package models

case class Contact(id: Long, name: String)

object Contact{

	def all(): List[Contact] = Nil

	def create(name: String) {}

	def delete(name: String) {}
	
}