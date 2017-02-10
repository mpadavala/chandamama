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
                            <table width="100%" class="table table-striped table-bordered table-hover" id="dataTables-example">
                                <thead>
                                    <tr>
                                        <th>Ticker</th>
                                        <th>Name</th>
                                        <th>Price</th>
                                        <th>Percent Gain</th>
                                        <th>Total Volume</th>
                                        <th>MarketCap</th>
                                        <th>EPS</th>
                                        <th>PE</th>
                                        <th>52 Week Low</th>
                                        <th>52 Week High</th>
                                        <th>Trade Date</th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <tr class="odd gradeX">
                                        <td>Trident</td>
                                        <td>Internet Explorer 4.0</td>
                                        <td>Win 95+</td>
                                        <td class="center">4</td>
                                        <td class="center">X</td>
                                        <td>Trident</td>
                                        <td>Internet Explorer 4.0</td>
                                        <td>Win 95+</td>
                                        <td class="center">4</td>
                                        <td class="center">X</td>
                                        <td>Trident</td>
                                    </tr>
                                    <tr class="even gradeC">
                                        <td>Trident</td>
                                        <td>Internet Explorer 5.0</td>
                                        <td>Win 95+</td>
                                        <td class="center">5</td>
                                        <td class="center">C</td>
                                        <td>Trident</td>
                                        <td>Internet Explorer 4.0</td>
                                        <td>Win 95+</td>
                                        <td class="center">4</td>
                                        <td class="center">X</td>
                                        <td>Trident</td>
                                    </tr>
                                    <tr class="odd gradeA">
                                        <td>Trident</td>
                                        <td>Internet Explorer 5.5</td>
                                        <td>Win 95+</td>
                                        <td class="center">5.5</td>
                                        <td class="center">A</td>
                                        <td>Trident</td>
                                        <td>Internet Explorer 4.0</td>
                                        <td>Win 95+</td>
                                        <td class="center">4</td>
                                        <td class="center">X</td>
                                        <td>Trident</td>
                                    </tr>
                                    <tr class="even gradeA">
                                        <td>Trident</td>
                                        <td>Internet Explorer 6</td>
                                        <td>Win 98+</td>
                                        <td class="center">6</td>
                                        <td class="center">A</td>
                                        <td>Trident</td>
                                        <td>Internet Explorer 4.0</td>
                                        <td>Win 95+</td>
                                        <td class="center">4</td>
                                        <td class="center">X</td>
                                        <td>Trident</td>
                                    </tr>
                                    <tr class="gradeA">
                                        <td>Gecko</td>
                                        <td>Firefox 1.0</td>
                                        <td>Win 98+ / OSX.2+</td>
                                        <td class="center">1.7</td>
                                        <td class="center">A</td>
                                        <td>Trident</td>
                                        <td>Internet Explorer 4.0</td>
                                        <td>Win 95+</td>
                                        <td class="center">4</td>
                                        <td class="center">X</td>
                                        <td>Trident</td>
                                    </tr>
                                    <tr class="gradeC">
                                        <td>KHTML</td>
                                        <td>Konqureror 3.1</td>
                                        <td>KDE 3.1</td>
                                        <td class="center">3.1</td>
                                        <td class="center">C</td>
                                        <td>Trident</td>
                                        <td>Internet Explorer 4.0</td>
                                        <td>Win 95+</td>
                                        <td class="center">4</td>
                                        <td class="center">X</td>
                                        <td>Trident</td>
                                    </tr>
                                    <tr class="gradeX">
                                        <td>Tasman</td>
                                        <td>Internet Explorer 4.5</td>
                                        <td>Mac OS 8-9</td>
                                        <td class="center">-</td>
                                        <td class="center">X</td>
                                        <td>Trident</td>
                                        <td>Internet Explorer 4.0</td>
                                        <td>Win 95+</td>
                                        <td class="center">4</td>
                                        <td class="center">X</td>
                                        <td>Trident</td>
                                    </tr>
                                    <tr class="gradeU">
                                        <td>Other browsers</td>
                                        <td>All others</td>
                                        <td>-</td>
                                        <td class="center">-</td>
                                        <td class="center">U</td>
                                        <td>Trident</td>
                                        <td>Internet Explorer 4.0</td>
                                        <td>Win 95+</td>
                                        <td class="center">4</td>
                                        <td class="center">X</td>
                                        <td>Trident</td>
                                    </tr>
                                </tbody>
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
        $('#dataTables-example').DataTable({
            responsive: true
        });
    });
    </script>

</body>

</html>
