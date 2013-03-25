package controllers

import play.api._
import play.api.mvc._

import models.Contact

object Application extends Controller {
  
  def index = Action {
  	// Logger.info("db "+Contact.mongoColl.toString)
  	
    Ok(views.html.index())
  }
  
  def contacts = Action {
  	val list = Contact.all()
  	Ok(list)
  }

  def retrieveContact(id: String) = Action {
    val output = Contact.get(id)
    Ok(output)
  }

  def newContact = Action { request =>
  	val body = request.body
  	val name = body.asFormUrlEncoded.get("contact[name]")(0)
  	val email = body.asFormUrlEncoded.get("contact[email]")(0)
  	val address = body.asFormUrlEncoded.get("contact[address]")(0)
  	val phone = body.asFormUrlEncoded.get("contact[phone]")(0)
  	Contact.create(name, address, email, phone)
  	Ok(name+", "+address+", "+phone+", "+email)
  }

  def deleteContact(id: String) = TODO
}