<style lang="less">
    @import './login.less';
</style>
<template>
    <div class="login" @keydown.enter="handleSubmit">
        <div class="login-con">
            <Card :bordered="false">
                <p slot="title">
                    <Icon type="log-in"></Icon>
                    欢迎登录
                </p>
                <div class="form-con">
                    <Form ref="loginForm" :model="form" :rules="rules">
                        <FormItem prop="userName">
                            <Input v-model="form.userName" placeholder="请输入用户名">
                            <span slot="prepend">
                                    <Icon :size="16" type="person"></Icon>
                                </span>
                            </Input>
                        </FormItem>
                        <FormItem prop="password">
                            <Input type="password" v-model="form.password" placeholder="请输入密码">
                            <span slot="prepend">
                                    <Icon :size="14" type="locked"></Icon>
                                </span>
                            </Input>
                        </FormItem>
                        <FormItem>
                            <Button @click="handleSubmit" type="primary" long>登录</Button>
                        </FormItem>
                    </Form>
                    <p class="login-tip">输入任意用户名和密码即可</p>
                </div>
            </Card>
        </div>
    </div>
</template>
<script>
    import Cookies from 'js-cookie';
    import axios from 'axios'

    export default {
        data() {
            return {
                form: {
                    userName: 'dyenigma',
                    password: 'admin'
                },
                rules: {
                    userName: [
                        {required: true, message: '账号不能为空', trigger: 'blur'}
                    ],
                    password: [
                        {required: true, message: '密码不能为空', trigger: 'blur'}
                    ]
                }
            };
        },
        methods: {
            handleSubmit() {

                let that = this;
                this.$refs.loginForm.validate((valid) => {
                    if (valid) {
                        let urlStr = '/api/login';
                        let postData = {userName: this.form.userName, password: this.form.password}

                        axios.post(urlStr, postData).then(function (response) {
                            console.log(response);

                            //登录成功保存用户登录状态
                            if (response.data.status === true) {

                                Cookies.set('user', response.data.data.account);
                                that.$store.commit('setAvator', 'https://ss1.bdstatic.com/70cFvXSh_Q1YnxGkpoWK1HF6hhy/it/u=3448484253,3685836170&fm=27&gp=0.jpg');

                                //设置当前用户权限，权限列表将与/router/router.js中配置的节点access字符串相匹配确定是否显示此菜单，匹配操作见/store/app.js/updateMenulist()
                                Cookies.set('access', response.data.data.pmsnList);

                                //跳转到主页
                                that.$router.push({
                                    name: 'home_index'
                                });
                            } else {
                                that.$Message.warning(response.data.message);
                            }
                        }).catch(function (error) {
                            that.$Message.warning('登录出错');
                        });
                    }
                });
            }
        }
    };
</script>
<style>
</style>
