export default class MapController{
    /*@ngInject*/
    constructor($state, $scope, DataFactory) {
        this.$scope = $scope;
        this.$state = $state;
        this.DataFactory = DataFactory;
        this.rawData = DataFactory.getCountryChartData();


        this._parseChartData();
        this._createChart('Emission per Country');

        this.$scope.$on('chartDataChangedEvent', (event, data) => {
            this.rawData = DataFactory.getChartData(data.aggregation)
            this._parseChartData();
            this._createChart(data.chartTitle);
        })


    }

    _parseChartData() {
        this.labels = [];
        this.values = [];
        this.rawData.forEach(item => {
            this.labels.push(item.label);
            this.values.push(item.emission);
        })


    }

    _createChart(title) {
        this.emission = {
            labels: this.labels,
            datasets: [{
                label: title,
                data: this.values,
                borderWidth: 1
            }]
        };
    }
}