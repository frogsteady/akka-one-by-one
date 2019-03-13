package persistence

import akka.persistence._
import akka.actor.{Actor, ActorRef, ActorSystem, Props}
import com.typesafe.config.{Config, ConfigFactory}


object Persistent extends App {
  import persistence.Counter._


  val system = ActorSystem("persistent-actors", ConfigFactory.load("application.conf"))

  val counter = system.actorOf(Props[Counter])

  counter ! Cmd(Increment(3))

  counter ! Cmd(Increment(5))

  counter ! Cmd(Decrement(3))

  counter ! "print"

  Thread.sleep(1000)

  system.terminate()

}






