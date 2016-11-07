export default function Router ($urlRouterProvider, $locationProvider, $stateProvider, USER_ROLES) {

    $stateProvider
        .state('login', {
            url: '/login',
            views: {
                main: {
                    template: require('../html/login.html'),
                    controller: 'LoginController',
                    controllerAs: 'login'
                }
            }
        })
        .state('logout', {
            url: '/logout',
            views: {
                main: {
                    template: require('../html/logout.html'),
                    controller: 'LogoutController',
                    controllerAs: 'logout'
                }
            },
            data: {
                //rolesAllowed: [USER_ROLES.all]
            }
        })
        .state('admin', {
            url: '/admin',
            views: {
                main: {
                    template: require('../html/admin.html'),
                    controller: 'AdminController',
                    controllerAs: 'admin'
                }
            },
            data: {
                menuTab: 'admin'
                //rolesAllowed: [USER_ROLES.admin]
            }
        })
        .state('app', {
            abstract: true,
            views: {
                main: {
                    template: require('../html/app.html'),
                    controller: 'AppController',
                    controllerAs: 'app'
                }
            }
        })
        .state('app.table', {
            url: '/table',
            views: {
                app: {
                    template: require('../html/table.html'),
                    controller: 'TableController',
                    controllerAs: 'table'
                }
            }
        })
        .state('app.chart', {
            url: '/chart',
            views: {
                app: {
                    template: require('../html/chart.html'),
                    controller: 'ChartController',
                    controllerAs: 'chart'
                }
            }
        })
        .state('app.map', {
            url: '/map',
            views: {
                main: {
                    template: require('../html/map.html'),
                    controller: 'MapController',
                    controllerAs: 'map'
                }
            },
            params:{
                app : null
            },
            data: {
                //rolesAllowed: [USER_ROLES.user, USER_ROLES.admin]
            }
        });


    $locationProvider.html5Mode(false);
    $urlRouterProvider.otherwise('/login');
    
}

Router.$inject = ['$urlRouterProvider', '$locationProvider', '$stateProvider'];