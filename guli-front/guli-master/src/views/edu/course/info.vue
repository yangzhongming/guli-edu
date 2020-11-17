<template>
  <div class="app-container">
    <h2 style="text-align: center;">发布新课程</h2>

    <el-steps :active="1" process-status="wait" align-center style="margin-bottom: 40px;">
      <el-step title="填写课程基本信息" />
      <el-step title="创建课程大纲" />
      <el-step title="提交审核" />
    </el-steps>

    <el-form label-width="120px">
      <el-form-item label="课程标题">
        <el-input v-model="courseInfo.title" placeholder="示例：机器学习课程" />
      </el-form-item>

      <el-form-item label="课程分类">
        <el-select v-model="courseInfo.subjectParentId" placeholder="一级分类" @change="subjectLevelOneChanged">
          <el-option v-for="subject in subjectOneList" :key="subject.id" :label="subject.title" :value="subject.id" />
        </el-select>
        <el-select v-model="courseInfo.subjectId" placeholder="二级分类">
          <el-option v-for="subject in subjectTwoList" :key="subject.id" :label="subject.title" :value="subject.id" />
        </el-select>
      </el-form-item>

      <el-form-item label="课程讲师">
        <el-select v-model="courseInfo.teacherId" placeholder="请选择">
          <el-option v-for="teacher in teacherList" :key="teacher.id" :label="teacher.name" :value="teacher.id" />
        </el-select>
      </el-form-item>
      <el-form-item label="总课时">
        <el-input v-model="courseInfo.title" placeholder="示例：机器学习课程" />
      </el-form-item>

      <el-form-item label="课程简介">
        <tinymce v-model="courseInfo.description" :height="300" />
      </el-form-item>

      <el-form-item label="课程封面">
        <el-upload
          :show-file-list="false"
          :on-success="handleAvatarSuccess"
          :before-upload="beforeAvatarUpload"
          :action="BASE_API+'/edu/oss/file'"
          class="avatar-uploader"
        >
          <img :src="courseInfo.cover">
        </el-upload>

      </el-form-item>
      <el-form-item label="课程价格">
        <el-input v-model="courseInfo.title" placeholder="示例：机器学习课程" />
      </el-form-item>
      <el-form-item>
        <el-button :disabled="saveBtnDisabled" type="primary">保存并下一步</el-button>
      </el-form-item>
    </el-form>
  </div>
</template>
<script>
import subject from '@/api/edu/subject'
import teacher from '@/api/edu/teacher'
import Tinymce from '@/components/Tinymce'
export default {

  components: { Tinymce },
  data() {
    return {
      saveBtnDisabled: false,
      courseInfo: {
        title: '',
        subjectId: '', // 二级分类id
        subjectParentId: '', // 一级分类id
        teacherId: '',
        lessonNum: 0,
        description: '',
        cover: '/static/01.jpg',
        price: 0
      },
      BASE_API: 'http://localhost:8002', // 接口API地址
      teacherList: [], // 封装所有的讲师
      subjectOneList: [], // 一级分类
      subjectTwoList: []// 二级分类
    }
  },
  created() {
    this.getOneSubject()
    this.getListTeacher()
  },
  methods: {
    // 查询一级分类
    getOneSubject() {
      subject.getSubjectList().then(response => { this.subjectOneList = response.data.list })
    },
    // 查询二级分类
    subjectLevelOneChanged(value) {
      for (var i = 0; i < this.subjectOneList.length; i++) {
        var oneSubject = this.subjectOneList[i]
        if (value === oneSubject.id) {
          this.subjectTwoList = oneSubject.children
          this.courseInfo.subjectId = ''
        }
      }
    },
    getListTeacher() {
      teacher.getListTeacher().then(reponse => { this.teacherList = reponse.data.item })
    },
    handleAvatarSuccess(res, file) {
      this.courseInfo.cover = res.data.url
    },
    // 上传之前调用的方法
    beforeAvatarUpload(file) {
      const isJPG = file.type === 'image/jpeg'
      const isLt2M = file.size / 1024 / 1024 < 2

      if (!isJPG) {
        this.$message.error('上传头像图片只能是 JPG 格式!')
      }
      if (!isLt2M) {
        this.$message.error('上传头像图片大小不能超过 2MB!')
      }
      return isJPG && isLt2M
    }
  }
}
</script>
