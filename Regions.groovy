import odata.utils.Odata
import groovy.json.JsonOutput

// use own java code for odata.services 
HttpURLConnection requestRegions = Odata.odataConnect("https://services.odata.org/V2/Northwind/Northwind.svc/Regions")
HttpURLConnection requestTerritories = Odata.odataConnect("https://services.odata.org/V2/Northwind/Northwind.svc/Territories")

// get Raw strings
String rawTerritories = Odata.getText(requestTerritories)
String rawRegions = Odata.getText(requestRegions)

// use XML parser
def slurper = new XmlSlurper()
territories = slurper.parseText(rawTerritories)
regions = slurper.parseText(rawRegions)

def Regions = [:] // store map ID - region for kinda "inner join"

def RegTer =  [:] // store nested structure
regions.entry.content["properties"].each {Regions[(String) it["RegionID"].text()] = (String) it["RegionDescription"].text().trim()
                                          RegTer[(String) Regions[it["RegionID"].text()]] = []}

territories.entry.content["properties"].each {RegTer[(String) Regions[it["RegionID"].text()]] << (String) it["TerritoryDescription"].text().trim()}

// convert json
def json = JsonOutput.toJson(RegTer)

// send to webhook.site
String siteToSend = "https://webhook.site/f8aa8936-afac-433d-bb7e-345154400f0d"
def answer = Odata.sendMessage(siteToSend, json)
println answer
