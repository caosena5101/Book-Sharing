import Cookies from 'js-cookie';
import axios from 'axios'

const user = {
    state: {},
    mutations: {
        logout (state, vm) {

            let that = this;

            Cookies.remove('user');
            Cookies.remove('password');
            Cookies.remove('access');
            // 恢复默认样式
            let themeLink = document.querySelector('link[name="theme"]');
            themeLink.setAttribute('href', '');
            // 清空打开的页面等数据，但是保存主题数据
            let theme = '';
            if (localStorage.theme) {
                theme = localStorage.theme;
            }
            localStorage.clear();
            if (theme) {
                localStorage.theme = theme;
            }

            let urlStr = '/api/logout';

            axios.post(urlStr).catch(function (error) {
                that.$Message.warning('请求出错');
            });
        }
    }
};

export default user;
