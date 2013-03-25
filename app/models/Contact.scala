package models

import com.mongodb.casbah.Imports._

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

	def get(id: Option[ObjectId]):String = {
		val res = mongoColl.findOneByID(id.asInstanceOf[ObjectId])
		return res.toString()
	}

	def transform():String = {
		val res = for { x <- mongoColl } yield x
  		val objects:String = res.mkString(",")
    	val output:String = "["+objects+"]"
    	return output
	}

	def delete(id: Long) {}
	
}