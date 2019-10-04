package timer

import grails.validation.ValidationException
import static org.springframework.http.HttpStatus.*

class ActivityController {

    ActivityService activityService

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond activityService.list(params), model:[activityCount: activityService.count()]
    }

    def show(Long id) {
        respond activityService.get(id)
    }

    def create() {
        respond new Activity(params)
    }

    def save(Activity activity) {
        if (activity == null) {
            notFound()
            return
        }

        try {
            activityService.save(activity)
        } catch (ValidationException e) {
            respond activity.errors, view:'create'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'activity.label', default: 'Activity'), activity.id])
                redirect activity
            }
            '*' { respond activity, [status: CREATED] }
        }
    }

    def edit(Long id) {
        respond activityService.get(id)
    }

    def update(Activity activity) {
        if (activity == null) {
            notFound()
            return
        }

        try {
            activityService.save(activity)
        } catch (ValidationException e) {
            respond activity.errors, view:'edit'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'activity.label', default: 'Activity'), activity.id])
                redirect activity
            }
            '*'{ respond activity, [status: OK] }
        }
    }

    def delete(Long id) {
        if (id == null) {
            notFound()
            return
        }

        activityService.delete(id)

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'activity.label', default: 'Activity'), id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'activity.label', default: 'Activity'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
