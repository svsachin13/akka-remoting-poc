package sachin.local

import akka.actor._

object Local extends App {

  implicit val system = ActorSystem("LocalSystem")
  val localActor = system.actorOf(Props(new LocalActor(system)), name = "LocalActor") // the sachin.local actor
  localActor ! "START" // start the action

}

class LocalActor(sys : ActorSystem) extends Actor {

  // create the remote actor (Akka 2.1 syntax)
  val remote = sys.actorSelection("akka.tcp://HelloRemoteSystem@127.0.0.1:5150/user/RemoteActor")
  var counter = 0

  def receive = {
    case "START" =>
      remote ! "Hello from the LocalActor"
    case msg: String =>
      println(s"LocalActor received message: '$msg'")
      if (counter < 5) {
        sender ! "Hello back to you"
        counter += 1
      }
  }
}
