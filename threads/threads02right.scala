#!/usr/bin/env scala

import java.util.concurrent.Executors
import scala.concurrent._

implicit val ec = new ExecutionContext {
    val threadPool = Executors.newFixedThreadPool(1000);
    def execute(runnable: Runnable) {
        threadPool.submit(runnable)
    }
    def reportFailure(t: Throwable) {}
}

(1 to 100).map{ n => Future{Thread.sleep(4000);println(n)}}
Thread.sleep(5000)
println("==========finished==========")
System.exit(1)

