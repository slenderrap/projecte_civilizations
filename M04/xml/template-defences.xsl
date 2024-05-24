<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
    <xsl:param name="paramType"/>
    <xsl:template match="/defence_units">
        <html>
            <head>
                <meta charset="UTF-8"/>
                <title>Defences</title>
                <link rel="stylesheet" type="text/css" href="css/defences.css" />
            </head>
            <body>
                <div class="contenedorDefenseUnits">
                    <xsl:for-each select="unit">
                        <div class="contendorDefenseUnit">
                            <h1><xsl:value-of select="name"/></h1>
                            <h3>Base damage: <xsl:value-of select="base_damage"/></h3>
                            <h3>Armor: <xsl:value-of select="armour"/></h3>
                            <h3>Waste chance: <xsl:value-of select="waste_chance"/></h3>
                            <h3>Attack again chance: <xsl:value-of select="attack_again_chance"/></h3>
                            <h3>Plus stats:</h3>
                            <ul>
                            	<li>Armor technology: <xsl:value-of select="plus_stats/armour_technology"/></li>
                            	<li>Attack technology: <xsl:value-of select="plus_stats/attack_technology"/></li>
                            	<li>Armor experience: <xsl:value-of select="plus_stats/armour_experience"/></li>
                            	<li>Attack experience: <xsl:value-of select="plus_stats/attack_experience"/></li>
                            	<li>Armor sanctified: <xsl:value-of select="plus_stats/armour_sanctified"/></li>
                            	<li>Attack sanctified: <xsl:value-of select="plus_stats/attack_sanctified"/></li>
                            </ul>
                            <h3>Costs:</h3>
                            <ul>
                            	<li>Food cost: <xsl:value-of select="costs/food_cost"/></li>
                            	<li>Wood cost: <xsl:value-of select="costs/wood_cost"/></li>
                            	<li>Iron cost: <xsl:value-of select="costs/iron_cost"/></li>
                            	<li>Mana cost: <xsl:value-of select="costs/mana_cost"/></li>
                            </ul>
                            <img class="sprite" src="./img/{sprite}" alt="Sprite"></img>
                        </div>
                    </xsl:for-each>
                </div>
            </body>
        </html>
    </xsl:template>
</xsl:stylesheet>
