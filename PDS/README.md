eAgenda UOC
===========

<p><b> Team: 3Layers </b></p>
<p> Members: </p>
        Antonio Díaz Pozuelo (adpozuelo@uoc.edu)
        Cristian Lozano Macías (clozanomac@uoc.edu)
        José Antonio Pérez Salvador (jperezsalva@uoc.edu)
        
This repositorie has been created to control file's versions of PDS eAgenda project.

Requisites
==========

- JDK 1.7 versión 1.7.0-67
- Ant, versión 1.9.4
- PostgreSQL versión 9.3.5-1
- Java connector Postgresql-9.3-1102.jdbc4
- JBoss, versión 7.1.1.Final
- JBoss Tools 4.1.2

Install
=======

<p> 3Layers eAgenda project is based upon UOC PracticalCaseStudyJEE environment. 
This environment are detailed in "Caso_practico_programacion.pdf" and "Tutotial_Instalacion_Codigo.pdf" files.
We only detail changes made upon this environment.</p>

<p> To Download eAgenda application: </p>

        git clone https://github.com/UOC-EIMT/3LAYERS.git
        cd 3LAYERS
        
<p> To enable and configure JAAS in Jboss <b>(root permissions could be required)</b>: </p>

        cp jboss_conf/standalone.xml $JBOSS_HOME/standalone/configuration/.
        
<p> To upgrade JSF, Servlet, JSP and JSTL to latest versions in Jboss <b>(root permissions could be required)</b>: </p>
        
        cp jboss_conf/jsf-api-2.2.8-02.txt $JBOSS_HOME/modules/javax/faces/api/main/jsf-api-2.2.8-02.jar
        cp jboss_conf/jsf-impl-2.2.8-02.txt $JBOSS_HOME/modules/com/sun/jsf-impl/main/jsf-impl-2.2.8-02.jar
        cp jboss_conf/javax.servlet-api-3.1.0.txt $JBOSS_HOME/modules/javax/servlet/api/main/javax.servlet-api-3.1.0.jar
        cp jboss_conf/javax.servlet.jsp-api-2.3.1.txt $JBOSS_HOME/modules/javax/servlet/jsp/api/main/javax.servlet.jsp-api-2.3.1.jar
        cp jboss_conf/javax.servlet.jsp.jstl-api-1.2.1.txt $JBOSS_HOME//modules/javax/servlet/jstl/api/main/javax.servlet.jsp.jstl-api-1.2.1.jar
        cp jboss_conf/module.xml.api $JBOSS_HOME/modules/javax/faces/api/main/module.xml
        cp jboss_conf/module.xml.impl $JBOSS_HOME/modules/com/sun/jsf-impl/main/module.xml
        cp jboss_conf/module.xml.servlet.api $JBOSS_HOME/modules/javax/servlet/api/main/module.xml
        cp jboss_conf/jbosgi-xservice.properties $JBOSS_HOME/modules/javax/servlet/api/main/.
        cp jboss_conf/module.xml.jsp.api $JBOSS_HOME/modules/javax/servlet/jsp/api/main/module.xml
        cp jboss_conf/module.xml.jstl $JBOSS_HOME/modules/javax/servlet/jstl/api/main/module.xml
        
<p> To start Jboss <b>(root permissions could be required)</b>: </p>

        $JBOSS_HOME/bin/standalone.sh

<p> To compile and deploy eAgenda application <b>(root permissions could be required)</b>: </p>

        ant

<p> To use eAgenda application: </p>

        Connect to http://localhost:8080/eAgenda/
        
<p><b> Create root user with right-up corner button to start using eAgenda. </b></p>

<p> If you want to load demo initial data, execute "initSql.sql" file in PostgreSQL. </p>
<p> Default demo data user/password: plopez@uoc.edu/uoc </p>

