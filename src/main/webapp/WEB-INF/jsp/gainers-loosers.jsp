<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>

        <div id="page-wrapper">
        
            <div class="row">
                <div class="col-lg-12">
                    <h1 class="page-header">Gainers & Loosers</h1>
                </div>
                <!-- /.col-lg-12 -->
            </div>
            
            <!-- /.row -->
            <div class="row">
                <div class="col-lg-12">
                    <div class="panel panel-default">
                        <div class="panel-heading">
                           Gainers
                        </div>
                        <!-- /.panel-heading -->
                        <div class="panel-body">
                           
                           
                           
                           <table id="example" class="table table-striped table-bordered table-hover" " cellspacing="0" width="100%">
        <thead>
            <tr>
                <th>Ticker</th>
                 <th>Name</th>
                  <th>Last Trade</th>
                   <th>Gain or Loss</th>
                    <th>Market Cap</th>
                     <th>PE</th>
                      <th>EPS</th>
               
               
               
               
            </tr>
        </thead>
        <tfoot>
       <tr>
			<th>Ticker</th>
            <th>Name</th>
             <th>Last Trade</th>
              <th>Gain or Loss</th>
               <th>Market Cap</th>
                <th>PE</th>
                 <th>EPS</th>
	              
                              
            </tr>
        </tfoot>
    </table>
    
    
                            <!-- /.table-responsive -->
                            
                            
                            
                        </div>
                        <!-- /.panel-body -->
                    </div>
                    <!-- /.panel -->
                </div>
                <!-- /.col-lg-12 -->
            </div>
            <!-- /.row -->
            

    <!-- Page-Level Demo Scripts - Tables - Use for reference -->
    <script>
    
    
    
    
    $(document).ready(function() {
        $('#example').DataTable( {
            "ajax": "http://localhost:9000/finance/stocks/gainers.json?marketcap=1B&totalvolume=100K&orderby=percentgainorloss",
            "columns": [
                { "data": "ticker" },
                { "data": "companyName" },
                { "data": "lastTrade" },
                { "data": "gainOrLoss" },
                { "data": "marketCap" },
                { "data": "pe" },
                { "data": "eps" }
               
            ]
        } );
    } );
    
    
    </script>

</body>

</html>
