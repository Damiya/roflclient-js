/*
 * Copyright 2014 Kate von Roeder (katevonroder at gmail dot com)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.itsdamiya.fateclasher

import com.gvaneyck.rtmp.ServerInfo
import akka.actor.ActorSystem
import akka.util.Timeout
import scala.concurrent.duration._
import com.typesafe.config.ConfigFactory
import com.itsdamiya.fateclasher.commands.{LoginWithToken, LoginWithCredentials}
import com.itsdamiya.fateclasher.platform.RTMPSClient
import com.itsdamiya.fateclasher.loginqueue.LQToken

object TempMain extends App {
  implicit val timeout = Timeout(1 minute)

  implicit val system = ActorSystem()
  val server = ServerInfo.NA

  val conf = ConfigFactory.load("login")
  val username = conf.getString("fateTester.username")
  val password = conf.getString("fateTester.password")

  val platformClient = system.actorOf(RTMPSClient(server), "platform")
  platformClient ! LoginWithToken(LQToken(1, "", "", None, None, "", 1, ""), "3.14.159")
}
