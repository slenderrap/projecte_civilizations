<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
    <xsl:param name="paramType"/>
    <xsl:template match="/defence_units">
        <html>
            <head>
                <meta charset="UTF-8"/>
                <title>Defences</title>
                <link rel="stylesheet" type="text/css" href="css/defences.css" />
                <link href="https://fonts.googleapis.com/css2?family=Caudex:ital,wght@0,400;0,700;1,400;1,700&amp;display=swap" rel="stylesheet"/>
            </head>
            <body>
                <div class="contendor">
                    <div class="titulo">
                        <h1>Defences</h1>
                    </div>
                    <div class="contenedorDefenseUnits">
                        <xsl:for-each select="unit">
                            <div class="contenedorDefenseUnit">
                                <div class= "contenidoDefenseUnit">
                                    <h1><xsl:value-of select="name"/></h1>
                                    <h3>Base stats:</h3>
                                    <ul>
                                        <li>Base damage: <xsl:value-of select="base_damage"/></li>
                                        <li>Armor: <xsl:value-of select="armour"/></li>
                                        <li>Waste chance: <xsl:value-of select="waste_chance"/></li>
                                        <li>Attack again chance: <xsl:value-of select="attack_again_chance"/></li>
                                    </ul>
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
