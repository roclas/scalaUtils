#!/bin/sh
exec scala -cp lib/sqljdbc4-2.0.jar:. "$0" "$@"
!#

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

class RsIterator(rs: ResultSet) extends Iterator[ResultSet] {
    def hasNext: Boolean = rs.next()
    def next(): ResultSet = rs
}

val userName="myuser"
val password= "mypassword"
val url = "jdbc:sqlserver://10.200.300.400:1433;databaseName=mydatabase"
Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
val conn = DriverManager.getConnection(url, userName, password)


def printTable(query:String)={
 val stmt = conn.createStatement()
 val resultset = stmt.executeQuery(query)
 var tableWidth=resultset.getMetaData.getColumnCount

 val rs = new RsIterator(resultset)
 (1 to resultset.getMetaData.getColumnCount).map(resultset.getMetaData.getColumnLabel(_)) foreach { x=>
	print(s"${x.toString}\t")
 }
 println("")


 val tablesData=rs.map(x=>(1 to tableWidth).map(x.getString(_)))
 tablesData foreach { vect=> 
	val r=vect.foldLeft(""){(a,b)=>
		val c=b match {
			case null=>""
			case an:Any=>an
		}
		a+c.replace("\t"," ")+"\t"
	}
	println(r)
	}
}
//"select * from information_schema.columns " //TO LIST TABLES
if(args.length==0)println("usage: <script> 'select * from mytable by ID OFFSET  5 ROWS FETCH NEXT 5 ROWS ONLY' ")
else args foreach printTable


