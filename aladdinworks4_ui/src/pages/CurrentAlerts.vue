<template>
  <div class="content">
    <div class="row">
      <div class="col-12">
        <card class="card-plain">
          <!-- <template slot="header">
            <h4 class="card-title">Table on Plain Background</h4>
            <p class="category">Here is a subtitle for this table</p>
          </template>-->
          <div class="table-full-width text-left">
            <currentAlert-table
            v-if="currentAlerts && currentAlerts.length > 0"
				:title="table1.title"
				:sub-title="table1.subTitle"
				:currentAlerts="currentAlerts"
				:totalElements="totalElements"
				:page="page"
				:columns="table1.columns"
 				@get-all-current-alerts="getAllCurrentAlerts"
             >

            </currentAlert-table>
          </div>
        </card>
      </div>

    </div>
  </div>
</template>
<script>
import { Card } from "@/components/Card";

import CurrentAlertTable from "@/components/CurrentAlertTable";
import CurrentAlertService from "../services/CurrentAlertService";

const tableColumns = [];
const tableData = [
];

export default {
  components: {
    Card,
    CurrentAlertTable,
  },
  data() {
    return {
      currentAlerts: [],
	  totalElements: 0,
      page: 0,
      searchQuery: '',     
      table1: {
        title: "Simple Table",
        columns: [...tableColumns],
        data: [...tableData],
      },
    };
  },
  methods: {
    async getAllCurrentAlerts(sortBy='currentAlertId',sortOrder='true',page="0",size="50") {
      try {
        try {
			const searchDTO = {
				sortBy: sortBy, 
				sortOrder: sortOrder, 
				searchQuery: this.searchQuery,
				page: page, 
				size: size
			};
	        let response = await CurrentAlertService.getAllCurrentAlerts(searchDTO);
	        if (!response.data.empty) {
		        if (response.data.currentAlerts.length) {
					this.currentAlerts = response.data.currentAlerts;
				}
      			this.totalElements = response.data.totalElements;
      			this.page = response.data.page;
	        }
        } catch (error) {
          console.error("Error fetching currentAlerts:", error);
        }
        
      } catch (error) {
        console.error("Error fetching currentAlert details:", error);
      }
    },
  },
  mounted() {
    this.getAllCurrentAlerts();
  },
  created() {
    this.$root.$on('searchQueryForCurrentAlertsChanged', (searchQuery) => {
    	this.searchQuery = searchQuery;
    	this.getAllCurrentAlerts();
    })
  }
};
</script>
<style></style>
