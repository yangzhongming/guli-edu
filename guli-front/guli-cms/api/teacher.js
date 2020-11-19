import request from '@/utils/request'

export default {
    getTeacherList(page,limit){
        return request({
            url:`/eduservice/teacherfront/getTeacherFrontList/${page}/${limit}`,
            method:'post'
        })
    }
}