<template>
  <div class="app-container">
    讲师表单
    <el-form label-width="120px">
      <el-form-item label="讲师名称">
        <el-input v-model="teacher.name" />
      </el-form-item>
      <el-form-item label="讲师排序">
        <el-input-number v-model="teacher.sort" controls-position="right" min="0" />
      </el-form-item>
      <el-form-item label="讲师头衔">
        <el-select v-model="teacher.level" clearable placeholder="请选择">
          <el-option :value="1" label="高级讲师" />
          <el-option :value="2" label="首席讲师" />
        </el-select>
      </el-form-item>
      <el-form-item label="讲师资历">
        <el-input v-model="teacher.career" />
      </el-form-item>
      <el-form-item label="讲师简介">
        <el-input v-model="teacher.intro" :rows="10" type="textarea" />
      </el-form-item>

      <!-- 讲师头像：TODO -->

      <el-form-item>
        <el-button :disabled="saveBtnDisabled" type="primary" @click="saveOrUpdate">保存</el-button>
      </el-form-item>
    </el-form>
  </div>
</template>

<script>
import teacherApi from '@/api/edu/teacher'

export default {
  data() {
    return {
      teacher: {
        name: '',
        sort: 0,
        level: 1,
        career: '',
        intro: '',
        avatar: ''
      }
    }
  },
  created() {
    this.init()
  },
  methods: {

    // 判断路径有无id值
    init() {
      if (this.$route.params && this.$route.params.id) {
        const id = this.$route.params.id
        this.getInfo(id)
      } else {
        // 清空表单
        this.teacher = {}
      }
    },

    // 根据id查询的方法
    getInfo(id) {
      teacherApi.getTeacherInfo(id).then(reponse => {
        this.teacher = reponse.data.teacher
      })
    },
    // 根据传入参数不同判断是添加还是修改
    saveOrUpdate() {
      if (!this.teacher.id) {
        this.saveTeacher()
      } else {
        this.updateTeacher()
      }
    },

    // 添加讲师
    saveTeacher() {
      teacherApi.addTeacher(this.teacher)
        .then(reponse => {
          this.$message({
            type: 'success',
            message: '添加成功！'
          })
          // 添加成功后，跳转至列表画面
          this.$router.push({ path: '/teacher/table' })
        }

        )
    },

    // 修改讲师
    updateTeacher() {
      teacherApi.updateTeacher(this.teacher).then(reponse => {
        this.$message({
          type: 'success',
          message: '修改成功！'
        })
        // 回到列表页面 路由跳转
        this.$router.push({ path: '/teacher/table' })
      })
    }
  }}
</script>
