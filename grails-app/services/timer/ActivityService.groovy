package timer

import grails.gorm.services.Service

@Service(Activity)
interface ActivityService {

    Activity get(Serializable id)

    List<Activity> list(Map args)

    Long count()

    void delete(Serializable id)

    Activity save(Activity activity)

}