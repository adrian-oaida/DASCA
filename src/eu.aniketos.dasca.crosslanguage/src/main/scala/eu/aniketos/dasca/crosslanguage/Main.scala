/*
 * (C) Copyright 2010-2015 SAP SE.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 */

package eu.aniketos.dasca.crosslanguage

import java.io.File
import scala.xml.Elem
import com.ibm.wala.ipa.callgraph._
import scala.collection.JavaConverters._
import eu.aniketos.dasca.crosslanguage.builder._
import scala.collection.mutable.ListBuffer
import java.lang.management.ManagementFactory
import eu.aniketos.dasca.crosslanguage.util.Util
import org.slf4j.LoggerFactory
import com.typesafe.scalalogging.Logger
import java.io.FileOutputStream
import java.io.FileInputStream
import java.io.ObjectOutputStream
import java.io.ObjectInputStream
import java.io.NotSerializableException
import com.ibm.wala.ipa.callgraph.CallGraph
import com.ibm.wala.util.strings.Atom;

import org.mozilla.javascript.serialize.ScriptableOutputStream
import eu.aniketos.dasca.crosslanguage.builder.algorithms.ReachabilityChecker

object Main {
  def testAllApps = {
                    val options = List()
                        implicit val logger = Logger(LoggerFactory.getLogger(getClass.toString))
                        
//                        val apk = new File("depend/WhodiniConsumer-2.9.0.2.apk")
                        val location = new File("/Users/dude/playground/top1000apps/")
                        var counter = 0;
                        var apksCateg: Map[String,List[String]]= Map()
                        for(category <- location.listFiles()){
                            logger.info(s"category $category")
                            
                            if(!category.getName().startsWith("."))
                            {
                              var foundApks:List[String] = List()
                              val apkDirectory = new File(category, "apks")
                              var apksInCategory = 0
                              var apksCouldNotBeDecoded = 0
                              for(apk <- apkDirectory.listFiles()){
                                if(apk.getName.contains(".apk")){
//                                  logger.info(s"building CG for $apk")
//                                  try{
//                                  val builder = CordovaCGBuilder(apk)
//                                  builder.setOptions(options: _*)
//                                  var mgc= builder.createCallGraph
//                                  if(mgc != null)
//                                    foundApks::=apk.toString()
//                                  }catch {
//                                    case e: Exception => apksCouldNotBeDecoded+=1
//                                  }
                                  counter = counter + 1
                                  apksInCategory +=1
                                }
                               apksCateg+=(category.toString() -> foundApks)

                              }
                              logger.info(s"found $apksInCategory  $apksCouldNotBeDecoded")
                            }
                        }
                        apksCateg.keys.foreach{
                          i => 
                            logger.info(s"in " + i)
                            logger.info(s"found " + apksCateg(i))
                        }
                        logger.info(s"found $counter")
  }
    def main(args: Array[String]): Unit = {
        if (args.length < 1 || (args.length < 2 && args(0).charAt(0) == '-')) {
            println("You must at least provide the path to the apk!")
            
            return
        }

//        val apk = if (args(0).charAt(0) == '-') new File(args(1)) else new File(args(0))

        
        val apk = new File("depend/DVHMA.apk")
                    val options = List()
                        implicit val logger = Logger(LoggerFactory.getLogger(getClass.toString))
                        
//                        val apk = new File("depend/WhodiniConsumer-2.9.0.2.apk")
                                  val builder = CordovaCGBuilder(apk)
                                  builder.setOptions(options: _*)
                                  val mcg = builder.createCallGraph
                                  for (line <- Util.prettyPrintCrossTargets(mcg.getAllCrossTargets)) logger.info(line)

//                                  var mgc= builder.createCallGraph

                
                    
    }
}
