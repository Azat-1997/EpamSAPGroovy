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

<!-- define a quot -->
<xsl:variable name="quot">
<xsl:text>'</xsl:text>
</xsl:variable>

<!-- add Capitalizing -->
 <xsl:variable name="vLower" select=
 "'abcdefghijklmnopqrstuvwxyz'"/>

 <xsl:variable name="vUpper" select=
 "'ABCDEFGHIJKLMNOPQRSTUVWXYZ'"/>

<xsl:template name="capitalize">
     <xsl:param name="value"/>
     <xsl:value-of select="concat(translate(substring($value, 1, 1), $vLower, $vUpper),
                                  translate(substring($value, 2), $vUpper, vLower))"/>

</xsl:template>

<!-- add Escaping -->

<xsl:template name="escaping">
    <xsl:param name="value"/>
    <xsl:variable name="escaped_symbols" select="concat(concat($quot, $sep, $quot),
                                                        concat($quot, $newstring, $quot))"/>

    <xsl:value-of select="concat($quot, $value, $quot, $sep)"/>
</xsl:template>


<!-- split id's, names and start_date on three blocks -->
<xsl:template match="/" name="main"> 

           <xsl:value-of select="concat('person_id',          $sep,
                                        'person_id_external', $sep,
                                        'first_name',         $sep,
                                        'last_name',          $sep,
                                        'start_date',         $newstring)"/>

            <xsl:for-each select="/result/sfobject/person">
               <!-- split id's, names and start_date on blocks -->
                <xsl:value-of select="concat(person_id, $sep)"/>
                <xsl:value-of select="concat(person_id_external, $sep)"/>


              <!-- Write variables with names -->
               <xsl:variable name="CapFirstName">

                <xsl:call-template name="capitalize">
                       <xsl:with-param name="value" select="personal_information/first_name"/>
                </xsl:call-template>
               
               </xsl:variable>

               <xsl:variable name="CapLastName">

                <xsl:call-template name="capitalize">
                       <xsl:with-param name="value" select="personal_information/last_name"/>
                </xsl:call-template>

                </xsl:variable>
                

               <!-- Call escaping template -->
                <xsl:call-template name="escaping">
                     <xsl:with-param name="value" select="$CapFirstName"/>
                </xsl:call-template>

                <xsl:call-template name="escaping">
                     <xsl:with-param name="value" select="$CapLastName"/>
                </xsl:call-template>


                <xsl:value-of select="concat(personal_information/start_date, $newstring)"/>

            </xsl:for-each>

</xsl:template>

</xsl:stylesheet>
