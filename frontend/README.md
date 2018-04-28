
##Install

	// install dependencies
	npm install

## Run
### Development

	npm run dev

### Production(Build)

	npm run build


## 文件结构

	shell
	.
	├── build  项目构建配置
	└── src
	    ├── images  图片文件
	    ├── libs  工具方法
	    ├── router  路由配置
	    ├── store  状态管理
	    ├── styles  样式文件
	    ├── template  模板文件
	    ├── vendors  公共库文件
	    └── views
	        ├── error-page  错误页面
	        ├── group  带二级目录的页面
	        │   ├── page1  二级页面1
	        │   ├── page2  二级页面2
	        ├── home  首页
	        ├── main-components  主组件
	        ├── page  一级目录页面
 

##iView配置
成功运行iview-admin之后，您就可以开始动手修改它，将自己的内容替换进去了。 首先最基础也是最重要的，应该说是路由配置，路由配置里目前有三种类型的配置，对应三种页面的展示类型。直接来看代码
###路由配置
- router.js
- 第一种类型：该页面在整个浏览器区域展现，代表性的有登录页、404页等

		export const loginRouter = {
		    path: '/login',  //必填
		    name: 'login',  // 必填，页面都以name值来加载
		    meta: {
		        title: 'Login - 登录'  // 非必填，若不填默认显示iview-admin（后面会介绍在哪修改默认显示的值）
		    },
		    component: resolve => { require(['./views/login.vue'], resolve); }  // 必填，用来加载该路由规则对应的视图，resolve用来控制异步加载
		};
- 第二种类型：该页面在Main组件的子页面区域展示，但不在左侧菜单栏显示，代表性的有首页、消息中心等

		export const otherRouter = {
		    path: '/',  // 必填
		    name: 'otherRouter',  // 必填，在面包屑处理中需要用到，值固定为otherRouter（或者可以在./src/libs/util.js中修改）
		    redirect: '/home',  // 选填，这里如果不填在浏览器地址栏输入域名后自动跳转到首页
		    component: Main,  // 必填，主组件，用于加载侧边栏和右侧面包屑、标签栏、工具条、子页面路由视图等
		    children: [  // 在Main右侧视图显示的页面都要作为otherRouter的children来添加
		        {  // home页面
		            path: 'home',  // 必填，在地址栏将以 '域名/home'的的形式呈现
		            title: '首页', // 必填，这个title会在标签栏显示
		            name: 'home_index',  // 必填，该页面是通过name值来加载的，切记每个路由对象的名字都要和其他的不一样
		            component: resolve => { require(['./views/home/home.vue'], resolve); }   // 必填
		        }
		    ]
		};
- 第三种类型：该页面在Main组件的子页面区域展示，且在左侧菜单栏显示，对应有两种情况

		export const appRouter = [{  // a.第一种情况：只有一级菜单
		        path: '/access',  // 必填
		        redirect: '/access/index',  // 选填，如果不填也会跳转到这个路径
		        icon: 'key',  // 必填，此icon将显示在左侧菜单栏
		        name: 'access',  // 必填
		        title: '权限管理',  // 必填，此title值将显示在左侧菜单栏
		        component: Main,  // 必填，且固定，用于加载Main组件
		        children: [  // 要显示在右侧区域的页面必须作为children来添加
		            { 
		                path: 'index',  // 必填
		                title: '权限管理',  // 必填，将显示在标签栏对应标签
		                name: 'access_index',  // 必填，且不能和其父路由的name不一致（与其他任何路由的name值都不能一致）
		                component: resolve => { require(['./views/access/access.vue'], resolve); }   // 必填
		            }
		        ]
		    },
		    {  // b.第二种情况：有二级菜单
		        path: '/component',  // 必填
		        redirect: '/component/text-editor',  // 选填，如果不填在地址栏输入'域名/access'时将默认打开此一级菜单对应的第一个二级菜单页面
		        icon: 'social-buffer',  // 必填，同上
		        name: 'component',  // 必填，同上
		        title: '组件',  // 必填，同上
		        component: Main,  // 必填，同上
		        children: [  // 必填，同上
		            {
		                path: 'text-editor',  // 必填，同上
		                icon: 'compose',  // 必填，同上
		                name: 'text-editor',  // 必填，同上
		                title: '富文本编辑器',  // 必填，将显示在左侧菜单栏二级菜单
		                component: resolve => { require(['./views/my_components/text-editor/text-editor.vue'], resolve); }  // 必填
		            },
		            {
		                path: 'md-editor',  // 必填，同上
		                icon: 'pound',  // 必填，同上
		                name: 'md-editor',  // 必填，同上
		                title: 'Markdown编辑器',  // 必填，同上
		                component: resolve => { require(['./views/my_components/markdown-editor/markdown-editor.vue'], resolve); }
		            },  // 必填
		        ]
		    }
		}
- 您还可以为页面配置权限，在左侧菜单初始化的时候，会通过当前登录用户的权限值来过滤路由配置，从而决定在左侧菜单栏显示哪些选项。权限配置很简单，只需在路由对象里设置'access'属性即可：

		{
	        path: '/access-test',
	        icon: 'lock-combination',
	        title: '权限测试页',
	        name: 'accesstest',
	        access: 0,  // 如果设置access值，那么当登录用户的权限值不为0时则该菜单及其二级菜单都不会出现在左侧菜单栏；
	                    // 如果不设置access值，那么该菜单默认显示；
	                    // access如果只有一个权限值过滤，那么直接写一个数字即可（如这的0）,如果有多个，则写成数组类型（如[0,1]）。
	        component: Main,
	        children: [
	            { path: 'index', title: '权限测试页', name: 'accesstest_index' }
	        ]
	    },
###Vue开发
- 开发的语法

## 功能

- 登录/登出
- 权限管理
    - 列表过滤
    - 权限切换
- 多语言切换
- 组件
    - 富文本编辑器
    - Markdown编辑器
    - 城市级联
    - 图片预览编辑
    - 可拖拽列表
    - 文件上传
    - 数字渐变
    - split-pane
- 表单编辑
    - 文章发布
    - 工作流
- 表格
    - 可拖拽排序
    - 可编辑表格
        - 行内编辑
        - 单元格编辑
    - 可搜索表格
    - 表格导出数据
        - 导出为Csv文件
        - 导出为Xls文件
    - 表格转图片
- 错误页面
    - 403页面
    - 404页面
    - 500页面
- 高级路由
    - 动态路由
    - 带参页面
- 换肤
- 收缩侧边栏
- tag标签导航
- 面包屑导航
- 全屏/退出全屏
- 锁屏
- 消息中心
- 个人中心

## 文件结构


	├── build  项目构建配置
	└── src
	    ├── images  图片文件
	    ├── libs  工具方法
	    ├── locale  多语言文件
	    ├── router  路由配置
	    ├── store  状态管理
	    ├── styles  样式文件
	    ├── template  模板文件
	    ├── vendors  公共库文件
	    └── views
	        ├── access  权限管理
	        ├── advanced-router  高级路由
	        ├── error_page  错误页面
	        ├── form  表单编辑
	        ├── home  首页
	        │   ├── components  首页组件
	        ├── international  多语言
	        ├── main_components  Main组件
	        │   ├── lockscreen  锁屏
	        │   ├── shrinkable-menu  可收缩菜单
	        │   └── theme-switch  主题切换
	        ├── message  消息中心
	        ├── my_components  业务组件
	        │   ├── area-linkage  中国行政区级联选择器
	        │   ├── count-to  数字渐变
	        │   ├── draggable-list  可拖拽列表
	        │   ├── file-upload  文件上传
	        │   ├── image-editor  图片预览编辑
	        │   ├── markdown-editor  Markdown编辑器
	        │   └── text-editor  富文本编辑器
	        ├── own-space  个人中心
	        └── tables  综合表格


## 配置权限控制说明
- src/views/access/access.vue
    
    权限示例页面，可以去掉，如果不去掉，一定要注意changeAccess方法中的处理逻辑
- src/store/modules/app.js

    登录时进行权限检查，见updateMenulist方法
- src/router/index.js

    见判断路由的处理代码块
- src/router/router.js

    见菜单中的access: ['pmsn:list']格式类内容
- src/libs/util.js

    showThisRoute方法处理逻辑


## Links

- [TalkingData](https://github.com/TalkingData)
- [iView](https://github.com/iview/iview)
- [Vue](https://github.com/vuejs/vue)
- [Webpack](https://github.com/webpack/webpack)


## License
[MIT](http://opensource.org/licenses/MIT)

Copyright (c) 2016-present, iView



