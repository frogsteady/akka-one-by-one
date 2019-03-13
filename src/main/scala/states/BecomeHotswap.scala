package states

import akka.actor.{Actor, ActorSystem, Props, Stash}
import states.UserStorage._

object BecomeHotswap extends App {
  import UserStorage._

  val system = ActorSystem("Hotswap-Become")

  val userStorage = system.actorOf(Props[UserStorage], "userStorage")


  userStorage ! Connect

  userStorage ! Operation(DBOperation.Create, Some(User("Admin", "admin@packt.com")))

  userStorage ! Disconnect

  Thread.sleep(100)

  system.shutdown()
}

case class User(username: String, email: String)

object UserStorage {

  trait DBOperation
  object DBOperation {
    case object Create extends DBOperation
    case object Update extends DBOperation
    case object Read extends DBOperation
    case object Delete extends DBOperation
  }

  case object Connect
  case object Disconnect
  case class Operation(dBOperation: DBOperation, user: Option[User])

}

class UserStorage extends Actor with Stash{

  def receive = disconnected

  def connected: Actor.Receive = {
    case Disconnect =>
      println("User Storage Disconnect from DB")
      context.unbecome()
    case Operation(op, user) =>
      println(s"User Storage receive ${op} to do in user: ${user}")

  }

  def disconnected: Actor.Receive = {
    case Connect =>
      println(s"User Storage connected to DB")
//      unstashAll()
      context.become(connected)
    case _ =>
//      stash()
  }
}

