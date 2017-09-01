grails.cache.config = {

    defaults {
        eternal false
        overflowToDisk false
        maxElementsInMemory 20000
        timeToLiveSeconds 3600
    }

    cache {
        name 'speciesListKvp'
        eternal false
        overflowToDisk false
        maxElementsInMemory 20000
        timeToLiveSeconds(3600 * 2) // 2 hours
    }
}
