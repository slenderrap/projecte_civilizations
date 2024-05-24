<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
    <xsl:param name="paramType"/>
    <xsl:template match="/buildings">
        <html>
            <head>
                <meta charset="UTF-8"/>
                <title>Buildings</title>
                <link rel="stylesheet" type="text/css" href="css/buildings.css" />
            </head>
            <body>
                <div class="contenedorBuildings">
                    <xsl:for-each select="building">
                        <div class="contenedorBuilding">
                            <h1><xsl:value-of select="name"/></h1>
                            <h3>Food cost: <xsl:value-of select="costs/food_cost"/></h3>
                            <h3>Iron cost: <xsl:value-of select="costs/iron_cost"/></h3>
                            <h3>Wood cost: <xsl:value-of select="costs/wood_cost"/></h3>
                            <img class="sprite" src="./img/{sprite}" alt="Sprite"></img>
                        </div>
                    </xsl:for-each>
                </div>
            </body>
        </html>
    </xsl:template>
</xsl:stylesheet>
