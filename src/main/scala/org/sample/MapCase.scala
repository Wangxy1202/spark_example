package org.wind3stone.spark.sample

;

import org.apache.spark.SparkConf;
import org.apache.spark.SparkContext;

object MapCase {

  def main(args: Array[String]): Unit = {
    //
    val conf = new SparkConf().setAppName("Join Application").setMaster("local");
    val sc = new SparkContext(conf)
    //
    val aFilePath = "file:///code/github/spark_example/data/a.txt"
    val bFilePath = "file:///code/github/spark_example/data/b.txt"
    val aDataRdd = sc.textFile(aFilePath, 2).cache()
    val aDataRddNew = aDataRdd.map(line => {
      val lineArray = line.split(";")
      (lineArray(0), lineArray(1))
    })
    val bDataRdd = sc.textFile(bFilePath, 2).cache()
    val bDataRddNew = bDataRdd.map(line => {
      var lineArray = line.split(";")
      (lineArray(0), lineArray(1))
    })
    val cDataRddNew = aDataRddNew.leftOuterJoin(bDataRddNew);
//    cDataRddNew.foreach(line => {
//      println(line)
//    });
    println(cDataRddNew.count())
    val outputFile = "file:///code/github/spark_example/data/output_rs"
    cDataRddNew.saveAsTextFile(outputFile)

    //    val mapRdd = sc.parallelize(List(1, 2, 3, 3))
    //    val mapOutRdd = mapRdd.map(x => x + 1)
    //    mapOutRdd.foreach(println)


    //    val aDataRddNew = aDataRdd.map(line =>
    //      line + "_NEWS")

    //
    //    val flatMapOutRdd = mapRdd.flatMap(x => x.to(3))
    //    flatMapOutRdd.foreach(println)

    //    3.to(8).foreach {
    //      i => println(i)
    //    }

    //    val a = sc.parallelize(List("dog", "tiger", "lion", "cat", "panther", " eagle"), 2)
    //    val b = a.map(x => (x, 1))
    //    b.collect.foreach(println(_))
    // 一样的
    //    b.collect.foreach(println)

  }

}
