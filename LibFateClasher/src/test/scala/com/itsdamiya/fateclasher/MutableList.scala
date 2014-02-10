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

import org.scalatest.{Suite, BeforeAndAfterEach}
import scala.collection.mutable

trait MutableList extends BeforeAndAfterEach {this: Suite =>
  val buffer = new mutable.MutableList[Byte]

  override def afterEach() {
    try super.afterEach()
    finally buffer.clear()
  }

}
