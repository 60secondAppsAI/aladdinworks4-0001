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
            <generatorUnit-table
            v-if="generatorUnits && generatorUnits.length > 0"
				:title="table1.title"
				:sub-title="table1.subTitle"
				:generatorUnits="generatorUnits"
				:totalElements="totalElements"
				:page="page"
				:columns="table1.columns"
 				@get-all-generator-units="getAllGeneratorUnits"
             >

            </generatorUnit-table>
          </div>
        </card>
      </div>

    </div>
  </div>
</template>
<script>
import { Card } from "@/components/Card";

import GeneratorUnitTable from "@/components/GeneratorUnitTable";
import GeneratorUnitService from "../services/GeneratorUnitService";

const tableColumns = [];
const tableData = [
];

export default {
  components: {
    Card,
    GeneratorUnitTable,
  },
  data() {
    return {
      generatorUnits: [],
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
    async getAllGeneratorUnits(sortBy='generatorUnitId',sortOrder='true',page="0",size="50") {
      try {
        try {
			const searchDTO = {
				sortBy: sortBy, 
				sortOrder: sortOrder, 
				searchQuery: this.searchQuery,
				page: page, 
				size: size
			};
	        let response = await GeneratorUnitService.getAllGeneratorUnits(searchDTO);
	        if (!response.data.empty) {
		        if (response.data.generatorUnits.length) {
					this.generatorUnits = response.data.generatorUnits;
				}
      			this.totalElements = response.data.totalElements;
      			this.page = response.data.page;
	        }
        } catch (error) {
          console.error("Error fetching generatorUnits:", error);
        }
        
      } catch (error) {
        console.error("Error fetching generatorUnit details:", error);
      }
    },
  },
  mounted() {
    this.getAllGeneratorUnits();
  },
  created() {
    this.$root.$on('searchQueryForGeneratorUnitsChanged', (searchQuery) => {
    	this.searchQuery = searchQuery;
    	this.getAllGeneratorUnits();
    })
  }
};
</script>
<style></style>
