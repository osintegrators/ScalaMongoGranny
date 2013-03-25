package models

import com.mongodb.casbah.Imports._
import java.lang.Object
import org.bson.types.ObjectId

object Contact{

	def mongoColl = MongoClient()("mongo_granny")("contacts")

	def all() : String = {
		mongoColl.find()
		return transform()
	}

	def create(name: String, address: String, email: String, phone: String) {
		val builder = MongoDBObject.newBuilder
		builder += "name" -> name
		builder += "address" -> address
		builder += "email" -> email
		builder += "phone" -> phone
		val newObj = builder.result
		mongoColl += newObj
	}

	def get(id: String):String = {
		val objId = new ObjectId(id)
		val contact = mongoColl.findOneByID(objId)
		val res = for { x <- contact } yield x
  		val ret:String = res.mkString(",")
    	return ret
	}

	def transform():String = {
		val res = for { x <- mongoColl } yield x
  		val objects:String = res.mkString(",")
    	val output:String = "["+objects+"]"
    	return output
	}

	def delete(id: String) = {
	  val objId = new ObjectId(id)
	  val newObj = MongoDBObject("_id"->objId)
	  mongoColl.remove(newObj)
	}
	
	def update(id: String, name: String, address: String, email: String, phone: String) = {
	  val objId = new ObjectId(id)
	  val where = MongoDBObject("_id"->objId)
	  val builder = MongoDBObject.newBuilder
	  builder += "name" -> name
	  builder += "address" -> address
	  builder += "email" -> email
	  builder += "phone" -> phone
	  val newObj = builder.result
	  mongoColl.update(where,newObj)
	}
	
}