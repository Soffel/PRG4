package de.blauePandas.LMSServer.core;

/**
 * @author havoc
 *
 * packt diese Datei am besten in euren .gitignore Ordner,
 * sie ist NUR dazu da, unsere localhost-mysql-server
 * mit jeweils anderen login-daten zu verwalten.
 *
 * Sprich: tragt hier eure Login-Daten vom MySQL ein
 * und pusht die Datei auf keinen Fall ins github
 *
 * Die Datei wird beim Serverstart in den ConnectionPool eingelesen
 */
public class localhost_connector {
    public static final String _HOSTNAME_ = "localhost/prgjava";
    public static final String _USERNAME_ = "root";
    public static final String _PASSWORD_ = "";
}
