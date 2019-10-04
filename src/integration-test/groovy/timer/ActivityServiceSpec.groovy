package timer

import grails.testing.mixin.integration.Integration
import grails.gorm.transactions.Rollback
import spock.lang.Specification
import org.hibernate.SessionFactory

@Integration
@Rollback
class ActivityServiceSpec extends Specification {

    ActivityService activityService
    SessionFactory sessionFactory

    private Long setupData() {
        // TODO: Populate valid domain instances and return a valid ID
        //new Activity(...).save(flush: true, failOnError: true)
        //new Activity(...).save(flush: true, failOnError: true)
        //Activity activity = new Activity(...).save(flush: true, failOnError: true)
        //new Activity(...).save(flush: true, failOnError: true)
        //new Activity(...).save(flush: true, failOnError: true)
        assert false, "TODO: Provide a setupData() implementation for this generated test suite"
        //activity.id
    }

    void "test get"() {
        setupData()

        expect:
        activityService.get(1) != null
    }

    void "test list"() {
        setupData()

        when:
        List<Activity> activityList = activityService.list(max: 2, offset: 2)

        then:
        activityList.size() == 2
        assert false, "TODO: Verify the correct instances are returned"
    }

    void "test count"() {
        setupData()

        expect:
        activityService.count() == 5
    }

    void "test delete"() {
        Long activityId = setupData()

        expect:
        activityService.count() == 5

        when:
        activityService.delete(activityId)
        sessionFactory.currentSession.flush()

        then:
        activityService.count() == 4
    }

    void "test save"() {
        when:
        assert false, "TODO: Provide a valid instance to save"
        Activity activity = new Activity()
        activityService.save(activity)

        then:
        activity.id != null
    }
}
