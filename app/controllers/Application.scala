package controllers

import play.api._
import play.api.mvc._

object Application extends Controller {
  
  def index = Action {
    Ok(views.html.index())
  }
  
  def contacts = TODO

  def newContact = TODO

  def deleteContact(id: Long) = TODO
}