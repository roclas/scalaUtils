#!/usr/bin/env scala

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future
import scala.concurrent.forkjoin._

(1 to 100).map{ n => Future{Thread.sleep(4000);println(n)}}
Thread.sleep(50000)
println("==========finished==========")
System.exit(1)

