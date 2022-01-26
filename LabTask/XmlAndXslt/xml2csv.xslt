<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" version="1.0"> 
   <!-- Fields: person_id, person_id_external,
        first_name, last_name, start_date  -->

<xsl:output method="text"/>

<!-- define end of row -->
<xsl:variable name="newstring">
<xsl:text>
</xsl:text>
</xsl:variable>

<!-- define a separator -->
<xsl:variable name="sep">
<xsl:text>,</xsl:text>
</xsl:variable>


<!-- add Capitalizing -->
 <xsl:variable name="vLower" select=
 "'abcdefghijklmnopqrstuvwxyz'"/>

 <xsl:variable name="vUpper" select=
 "'ABCDEFGHIJKLMNOPQRSTUVWXYZ'"/>


<xsl:template match="/"> 

            <xsl:value-of select="concat('person_id,person_id_external,first_name,last_name,start_date', $newstring)"/>
            <xsl:for-each select="/result/sfobject/person">


               <!-- split id's, names and start_date on three blocks -->
                <xsl:value-of select="concat(person_id, $sep,
                                             person_id_external, $sep)"/>

                <xsl:value-of select="concat(translate(substring(personal_information/first_name,1,1),
                                             $vLower, $vUpper),
                                             substring(personal_information/first_name, 2), $sep
                                            )"/>

                <xsl:value-of select="concat(translate(substring(personal_information/last_name,1,1),
                                             $vLower, $vUpper),
                                             substring(personal_information/last_name, 2), $sep
                                            )"/>
              
                <xsl:value-of select="concat(personal_information/start_date, $newstring)"/>

            </xsl:for-each>

    </xsl:template>


</xsl:stylesheet>
