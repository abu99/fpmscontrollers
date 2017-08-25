/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.fpms.controllers.test.common;

import java.util.Properties;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

/**
 *
 * @author faizbash
 */
public class ServiceLocator {
    private final Context context;

    /**
     * Initialize the Context
     * @throws NamingException
     */
    public ServiceLocator() throws NamingException {
        Properties props = new Properties();
        props.put(Context.INITIAL_CONTEXT_FACTORY,
                "org.apache.openejb.client.LocalInitialContextFactory");
        //disable in-vm use for serializable data types
        props.put("openejb.localcopy","false");
        
        //Config the DataSource with MySQL
        props.setProperty("myds", "new://Resource?type=DataSource");
        props.setProperty("myds.JdbcDriver", "com.mysql.jdbc.Driver");
        props.setProperty("myds.JdbcUrl", "jdbc:mysql://localhost:3306/fpms");
        props.setProperty("myds.UserName", "mysql_user");
        props.setProperty("myds.Password", "mysql_password");
        
        

        props.setProperty("myDataSource.JtaManaged", "true");

        this.context = new InitialContext(props);
    }

    /**
     *
     * @param name of the component to lookup
     * @return gets the lookup component
     */
    public Object lookup(String name) {
        try {
            return context.lookup(name);
        } catch (NamingException e) {
            throw new IllegalArgumentException(e);
        }
    }

    /**
     * This method conforms with the OpenEJB lookup format ({deploymentId}{interfaceType.annotationName})
     * The {deploymentId} is the name of the EJB class
     * whereas the {interfaceType.annotationName} is "Remote", "Local" "RemoteHome", or "LocalHome".
     * This method assumes that the interface simple name is defined to match the
     * format {deploymentId}{interfaceType.annotationName}.
     * The interface class should therefore be passed as the method argument.
     * For more info, check {@link http://openejb.apache.org/3.0/jndi-names.html}.
     * @param <T> Class type of component to lookup
     * @param type
     * @return the lookup component as the defined type
     */
    public <T> T lookup(Class<T> type) {
        System.out.println(".............");
        //
        String name = type.getSimpleName();
        System.out.println(name);
        return (T) lookup(name);
    }
}
