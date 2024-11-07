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
            <switchDevice-table
            v-if="switchDevices && switchDevices.length > 0"
				:title="table1.title"
				:sub-title="table1.subTitle"
				:switchDevices="switchDevices"
				:totalElements="totalElements"
				:page="page"
				:columns="table1.columns"
 				@get-all-switch-devices="getAllSwitchDevices"
             >

            </switchDevice-table>
          </div>
        </card>
      </div>

    </div>
  </div>
</template>
<script>
import { Card } from "@/components/Card";

import SwitchDeviceTable from "@/components/SwitchDeviceTable";
import SwitchDeviceService from "../services/SwitchDeviceService";

const tableColumns = [];
const tableData = [
];

export default {
  components: {
    Card,
    SwitchDeviceTable,
  },
  data() {
    return {
      switchDevices: [],
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
    async getAllSwitchDevices(sortBy='switchDeviceId',sortOrder='true',page="0",size="50") {
      try {
        try {
			const searchDTO = {
				sortBy: sortBy, 
				sortOrder: sortOrder, 
				searchQuery: this.searchQuery,
				page: page, 
				size: size
			};
	        let response = await SwitchDeviceService.getAllSwitchDevices(searchDTO);
	        if (!response.data.empty) {
		        if (response.data.switchDevices.length) {
					this.switchDevices = response.data.switchDevices;
				}
      			this.totalElements = response.data.totalElements;
      			this.page = response.data.page;
	        }
        } catch (error) {
          console.error("Error fetching switchDevices:", error);
        }
        
      } catch (error) {
        console.error("Error fetching switchDevice details:", error);
      }
    },
  },
  mounted() {
    this.getAllSwitchDevices();
  },
  created() {
    this.$root.$on('searchQueryForSwitchDevicesChanged', (searchQuery) => {
    	this.searchQuery = searchQuery;
    	this.getAllSwitchDevices();
    })
  }
};
</script>
<style></style>
