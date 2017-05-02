<template>
  <div class="head_bar">
    <div class="wrap">
      <img class="logo" src="./img/logo.png" height="206" width="500">
      <ul class="user" v-if="!isLogin">
        <router-link to="login">登录</router-link>
        <router-link to="register">注册</router-link>
      </ul>
      <Poptip trigger="hover" placement="bottom" class="avatar" v-if="isLogin" width="150">
          <img :src="getAvatar" alt="avatar" title="avatar">
          <div class="api link_box" slot="content">
            <ul>
              <li>{{user.nickName}}</li>
              <li><a href="#" >123456</a></li>
              <li><a href="#">123456</a></li>
              <li><a href="#" @click="loginOut">退出登录</a></li>
            </ul>
          </div>
      </Poptip>
      <ul class="nav">
        <router-link to="aboutMe">首页</router-link>
        <router-link to="aboutMe">文章</router-link>
        <router-link to="aboutMe">关于我</router-link>
      </ul>
    </div>
  </div> 
</template>

<script>
export default {
  data () {
    return {
      user:{
        type:Object
      }
    }
  },
  methods:{
    loginOut () {
      this.$http.post(this.URL.LOGIN_OUT).then((re) => {
        if (re.status === 200) {
          this.$store.dispatch('loginOut')
        }
      })
    }
  },
  watch:{
    isLogin () {
      this.user = this.$store.getters.getUser
    }
  },
  computed:{
    isLogin () {
      return this.$store.getters.isLogin
    },
    getAvatar () {
      return this.Global.NGINX + this.user.avatar
    }
  }
}
</script>


<style  lang="stylus" scoped>
  .head_bar
    position fixed
    top 0
    left 0
    height 50px
    width 100%
    font-size 10px
    background rgba(67, 179, 207,.8)
    z-index 100
    .wrap
      height 100%
      .logo
        height 40px
        width 97px
        margin-top 5px
        margin-right 20px
        float left
      .nav
        float left
        height 50px
        line-height 50px
        a
          color #fff
          font-size 14px
          text-decoration none
          float left
          height 50px
          padding 0 10px
          &:hover
            background #a6dbe4
      .user
        float right
        padding 18px 0
        a
          float left
          font-size 14px
          line-height 14px
          color #fff
          padding 0 10px
          border-right 1px solid #eee
          cursor pointer
          &:last-child
            border none
      .avatar
        height 50px
        width 50px
        float right
        padding 10px
        cursor pointer
        img
          display block
          height 30px
          width 30px
        .link_box
          li
            text-align center
            font-size  14px
</style>
