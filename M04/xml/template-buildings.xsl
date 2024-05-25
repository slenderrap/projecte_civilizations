<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
    <xsl:param name="paramType"/>
    <xsl:template match="/buildings">
        <html>
            <head>
                <meta charset="UTF-8"/>
                <title>Buildings</title>
                <link rel="stylesheet" type="text/css" href="css/buildings.css" />
                <link href="https://fonts.googleapis.com/css2?family=Caudex:ital,wght@0,400;0,700;1,400;1,700&amp;display=swap" rel="stylesheet"/>
            </head>
            <body>
                <div class="contendor">
                    <div class="titulo">
                        <h1>Buildings</h1>
                    </div>
                    <div class="contenedorBuildings">
                        <xsl:for-each select="building">
                            <div class="contenedorBuilding">
                                <div class= "contenidoBuilding">
                                    <h1><xsl:value-of select="name"/></h1>
                                    <h3>Food cost: <xsl:value-of select="costs/food_cost"/></h3>
                                    <h3>Iron cost: <xsl:value-of select="costs/iron_cost"/></h3>
                                    <h3>Wood cost: <xsl:value-of select="costs/wood_cost"/></h3>
                                </div>
                                <div class="foto">
                                    <img class="sprite" src="./img/{sprite}" alt="Sprite"></img>
                                </div>

                            </div>
                        </xsl:for-each>
                    </div>
                </div>
            </body>
        </html>
    </xsl:template>
</xsl:stylesheet>
