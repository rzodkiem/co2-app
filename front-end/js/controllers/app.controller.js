export default class AppController {

    constructor($state) {
        this.$state = $state;
        this.showFilters = false;
        this.showMenu = false;
    }

    openFilters () {
        this.showFilters = !this.showFilters;
        this.showMenu = false;
    }
    openMenu () {
        this.showMenu = !this.showMenu;
        this.showFilters = false;
    }
    closeMenus () {
        this.showMenu = false;
        this.showFIlters = false;
    }
    logout() {
        this.$state.go('login');
    }
}

AppController.$inject = ['$state'];