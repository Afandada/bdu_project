import Vue from 'vue'
import Router from 'vue-router'
//import HelloWorld from '@/components/HelloWorld'
import Login from '@/components/Login'
import MyMain from '@/components/yuanzhang/MyMain'
import UpdatePwd from '@/components/yuanzhang/UpdatePwd'
import TeacherManage from '@/components/yuanzhang/TeacherManage'
import ScoreManage from '@/components/teacher/ScoreManage'
import Jiaoxuerili from '@/components/teacher/Jiaoxuerili'
import Jiaoxuerizhi from '@/components/teacher/Jiaoxuerizhi'
import Childrens from '@/components/teacher/Childrens'
import ScoreQuery from '@/components/teacher/ScoreQuery'
import Jiaoxuedagang from '@/components/teacher/Jiaoxuedagang'
import People from '@/components/teacher/People'
import KaoCha from '@/components/teacher/KaoCha'
import KaoHe from '@/components/teacher/KaoHe'
import Pingshichengji from '@/components/teacher/Pingshichengji'
import DepManage from '@/components/yuanzhang/DepManage'
import PriManage from '@/components/yuanzhang/PriManage'
import SecManage from '@/components/yuanzhang/SecManage'
import JRiLi from '@/components/yuanzhang/JRiLi'
import Jxdg from '@/components/yuanzhang/Jxdg'
import Jxrz from '@/components/yuanzhang/Jxrz'
import Mjrili from '@/components/mishu/Mjrili'
import Mjxdg from '@/components/mishu/Mjxdg'
import Mjxrz from '@/components/mishu/Mjxrz'
import Mpeople from '@/components/mishu/Mpeople'
import Xjrili from '@/components/xizhuren/Xjrili'
import Xjxdg from '@/components/xizhuren/Xjxdg'
import Xjxrz from '@/components/xizhuren/Xjxrz'
import Xpeople from '@/components/xizhuren/Xpeople'
import MiShu from '@/components/mishu/MiShu'
import XiZhuRen from '@/components/xizhuren/XiZhuRen'

Vue.use(Router)

export default new Router({
  routes: [
    {
      path: '/',
      name: 'Login',
      component: Login
    },
   
    {
      path: '/childrens',
      name: 'childrens',
      component: Childrens,
     
      children: [
        {
          path:'people',
          name: 'people',
          component: People
        },
        {
          path:'jiaoxuerili',
          name: 'jiaoxuerili',
          component: Jiaoxuerili,
         
        },
        {
          path:'kaohe',
          name: 'kaohe',
          component: KaoHe,
         
        },
        {
          path:'kaocha',
          name: 'kaocha',
          component: KaoCha,
         
        },
        {
          path:'pingshichengji',
          name: 'pingshichengji',
          component: Pingshichengji,
         
        },
        {
          path:'jiaoxuerizhi',
          name: 'jiaoxuerizhi',
          component: Jiaoxuerizhi
        },
        {
          
          path: 'scorequery',
          name: 'scorequery',
          component: ScoreQuery
        }, 
        {
          
          path: 'jiaoxuedagang',
          name: 'jiaoxuedagang',
          component: Jiaoxuedagang
        }, 
      
      ]
    },
    {
      path:'/mishu',
      name: 'mishu',
      component: MiShu,
      children:[
        {
          path: 'mjrili',
          name: 'mjrili',
          component: Mjrili
        },
        {
          path: 'mjxdg',
          name: 'mjxdg',
          component: Mjxdg
        },
        {
          path: 'mjxrz',
          name: 'mjxrz',
          component: Mjxrz
        },
        {
          path: 'mpeople',
          name: 'mpeople',
          component: Mpeople
        },
      ]
    },
    {
      path: '/mymain',
      name: 'MyMain',
      component: MyMain,
      children: [
        {
          path: 'updatepwd',
          name: 'updatepwd',
          component: UpdatePwd
        },
        {
          path: 'teachermanage',
          name: 'teachermanage',
          component: TeacherManage
        },
        {
          path: 'jrili',
          name: 'jrili',
          component: JRiLi
        },
        {
          path: 'jxdg',
          name: 'jxdg',
          component: Jxdg
        },
        {
          path: 'jxrz',
          name: 'jxrz',
          component: Jxrz
        },
        {
          path: 'depmanage',
          name: 'depmanage',
          component: DepManage
        },
        {
          path: 'primanage',
          name: 'primanage',
          component: PriManage
        },
        {
          path: 'secmanage',
          name: 'secmanage',
          component: SecManage
        },
        {
          path: 'ScoreManage',
          name: 'ScoreManage',
          component: ScoreManage
        },
       
  ]},
  {
    path: '/xizhuren',
    name: 'XiZhuRen',
    component: XiZhuRen,
    children: [
      {
        path: 'xjrili',
        name: 'xjrili',
        component: Xjrili
      },
      {
        path: 'xjxdg',
        name: 'xjxdg',
        component: Xjxdg
      },
      {
        path: 'xjxrz',
        name: 'xjxrz',
        component: Xjxrz
      },
      {
        path: 'xpeople',
        name: 'xpeople',
        component: Xpeople
      },
      
]
}
  ]
})
