package fr.paris.lutece.plugins.publicdashboard.service;

import java.util.Optional;

import fr.paris.lutece.portal.service.cache.AbstractCacheableService;
import fr.paris.lutece.portal.web.xpages.XPage;

public class PublicDashboardCacheService extends AbstractCacheableService
{

    private static final String SERVICE_NAME = "PublicDashboard Cache Service";
    private static PublicDashboardCacheService _instance = new PublicDashboardCacheService( );

    /**
     * init
     */
    public PublicDashboardCacheService( )
    {
        initCache( );
    }

    /**
     * getter
     * 
     * @return the instance
     */
    public static PublicDashboardCacheService getInstance( )
    {

        return _instance;
    }

    /**
     * get the service name
     * 
     * @return the service name
     */
    public String getName( )
    {
        return SERVICE_NAME;
    }

    /**
     * get the resource
     * 
     * @param strId
     * @param user
     * @return the project
     */
    public Optional<XPage> getResource( String userid )
    {

        String cacheKey = getCacheKey( userid );

        Optional<XPage> r = Optional.ofNullable( (XPage) getFromCache( cacheKey ) );

        return r;
    }

    /**
     * get the cache key
     * 
     * @param strId
     * @param user
     * @return the key
     */
    public static String getCacheKey( String userid )
    {
        StringBuilder sbKey = new StringBuilder( );
        sbKey.append( "[publicdashboard:userid:" );

        if ( userid != null )
        {
            sbKey.append( userid );
        }
        else
        {
            return null;
        }
        sbKey.append( "]" );

        return sbKey.toString( );
    }

}
