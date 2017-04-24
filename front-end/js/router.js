export default function Router ($urlRouterProvider, $locationProvider, $stateProvider){

    $stateProvider
        .state('app', {
            abstract: true,
            views:{
                main:{
                    template: require('../html/app.html'),
                    controller: 'AppController',
                    controllerAs: 'app'
                }
            }
        })
        .state('app.main',{
            url: '/main',
            views:{
                app:{
                    template: require('../html/table.html'),
                    controller: 'TableController',
                    controllerAs: 'table'
                }
            }
        })
        .state('app.table', {
            url: '/table',
            views:{
                app:{
                    template: require('../html/table.html'),
                    controller: 'TableController',
                    controllerAs: 'table'
                }
            }
        })
        .state('app.chart', {
            url: '/chart',
            views:{
                app:{
                    template: require('../html/chart.html'),
                    controller: 'ChartController',
                    controllerAs: 'chart'
                }
            }
        })
        .state('app.maps', {
            url: '/maps',
            views:{
                app:{
                    template: require('../html/map.html'),
                    controller: 'MapController',
                    controllerAs: 'map'
                }
            }
        })
        .state('app.login', {
            url: '/login',
            views: {
                app: {
                    template: require('../html/login.html'),
                    controller: 'LoginController',
                    controllerAs: 'login'
                }
            }
        })
        .state('app.admin', {
            url: '/admin',
            views: {
                app: {
                    template: require('../html/admin.html'),
                    controller: 'AdminController',
                    controllerAs: 'admin'
                }
            }
        })

    ;

    $locationProvider.html5Mode(false);
    $urlRouterProvider.otherwise('/main');
}