import request from '@/utils/request'

export default {
    // 条件分页查询
    getCourseList(page,limit,search){
        return request({
            url:`/eduservice/coursefront/getFrontCourseList/${page}/${limit}`,
            method:'post',
            data:search
        })
    },
    // 查询所有分类方法
    getAllSubject(){
        return request({
            url: '/eduservice/subject/getAllSubject',
            method: 'get'
        })
    },
    // 查询课程详情
    getCourseInfo(id){
        return request({
            url: '/eduservice/coursefront/getFrontCourseInfo/'+id,
            method:'get'
        })
    }
}