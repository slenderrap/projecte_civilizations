'''
from lxml import etree
import os

# Llegir un arxiu XML
def read_xml(path):
   file = open(path, 'r', encoding='utf-8')
   string = file.read()
   file.close()
   return bytes(bytearray(string, encoding='utf-8'))

# Escriure un arxiu HTML
def write_html(path, html):
   file = open(path, 'w', encoding='utf-8')
   file.write(html)
   file.close()

# Crear un índex.html amb les 5 primeres noticies
def transform(xmlTree):
   # Crear l'arbre XSL per l'index de totes les cancons
   xslcancons = read_xml('./xml/template-special_units.xsl')
   xslTreecancons = etree.XML(xslcancons)

   # Transformar l'arxiu de dades-cancons.xml segons l'arxiu template-cancons.xsl i guardar-lo a index.html
   transform = etree.XSLT(xslTreecancons)
   htmlDom = transform(xmlTree)
   htmlResult = etree.tostring(htmlDom, pretty_print=True).decode('utf-8')
   write_html("./html/special_units.html", htmlResult)

# Crear l'arbre XML
xml = read_xml('./xml/special_units.xml')
xmlTree = etree.XML(xml)

# Generar l'arxiu de les 5 primeres noticies
transform(xmlTree)
'''



from lxml import etree
import os

# Llegir un arxiu XML
def read_xml(path):
   file = open(path, 'r', encoding='utf-8')
   string = file.read()
   file.close()
   return bytes(bytearray(string, encoding='utf-8'))

# Escriure un arxiu HTML
def write_html(path, html):
   file = open(path, 'w', encoding='utf-8')
   file.write(html)
   file.close()

# Crear un índex.html amb les 5 primeres noticies
def transform(xmlTree, templatename, htmlname):
   # Crear l'arbre XSL per l'index de totes les noticies
   xslarch = read_xml(templatename)
   xslTreearch = etree.XML(xslarch)
   # Transformar l'arxiu de dades-microsiervos.rss segons l'arxiu template-microsiervos.xsl
   transform = etree.XSLT(xslTreearch)
   htmlDom = transform(xmlTree)
   htmlResult = etree.tostring(htmlDom, pretty_print=True).decode('utf-8')
   write_html(htmlname, htmlResult)

# Buildings
xml = read_xml('./xml/buildings.xml')
xmlTree = etree.XML(xml)
transform(xmlTree, './xml/template-buildings.xsl',"./html/buildings.html")

# Defences
xml = read_xml('./xml/defences.xml')
xmlTree = etree.XML(xml)
transform(xmlTree, './xml/template-defences.xsl',"./html/defences.html")

# Attack Units
xml = read_xml('./xml/attack_units.xml')
xmlTree = etree.XML(xml)
transform(xmlTree, './xml/template-attack_units.xsl',"./html/attack_units.html")

# Defences
xml = read_xml('./xml/special_units.xml')
xmlTree = etree.XML(xml)
transform(xmlTree, './xml/template-special_units.xsl',"./html/special_units.html")