import org.scalatest._
import org.openqa.selenium.WebDriver
import org.openqa.selenium.htmlunit.HtmlUnitDriver
import selenium._
import com.dynatrace.webautomation.DynaTraceSeleniumRunner
import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner
import org.scalatest.FunSuite
import com.thoughtworks.selenium.Selenium
import com.dynatrace.webautomation.SeleniumConfiguration
import com.dynatrace.webautomation.DynaTraceSeleniumHelper
import com.thoughtworks.selenium.DefaultSelenium


//@RunWith(classOf[DynaTraceSeleniumRunner])
class BlogSpec extends FlatSpec with ShouldMatchers with WebBrowser {

   //implicit val webDriver: WebDriver = new HtmlUnitDriver
   //val host = "http://localhost:8080/"

  "The blog app home page" should "have the correct title" in {
    //go to (host + "/")
    //println(pageTitle)
    //pageTitle should be ("Home - Liferay")
    val selenium:Selenium=new DefaultSelenium("localhost", 4444, "*firefox", "http://localhost:8080")
    selenium.start()
    DynaTraceSeleniumHelper.forSelenium(selenium).open("/")     
    selenium.getTitle() should be ("Home - Liferay")
  }
}
  
