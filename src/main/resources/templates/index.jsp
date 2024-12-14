<!DOCTYPE html>
<html xmlns:th="http://www.thymeleaf.org">
<head>
    <meta charset="UTF-8">
    <title>Eks Profile Management</title>
    <meta charset="ISO-8859-1">
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="icon" href="https://mail.google.com/mail/u/0?ui=2&ik=19682974c8&attid=0.0.2&permmsgid=msg-f:1818233379813240290&th=193ba9a02286ade2&view=fimg&fur=ip&permmsgid=msg-f:1818233379813240290&sz=s0-l75-ft&attbid=ANGjdJ_a5GZTJZhCuKM9crfDBk9-3vP8odoth6GDGv2SkT7RR5S6hDrE94uc2OGcGyG0_D0aCGNu4QEHpmVLu6cZRtpTOIGzHFQ3JgL5lEa38Ay_F5HE-NcBW-Yf99Y&disp=emb&zw" type="image/x-icon"/>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/gh/gauravnewton/js-libs/bootstrap/css/bootstrap.min.css" />
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/gh/gauravnewton/js-libs/dataTable/css/dataTable.bootstrap.css" />
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/gh/gauravnewton/js-libs/dataTable/css/responsive.bootstrap.min.css" />
    <link rel="stylesheet" href="https://unpkg.com/dropzone@5/dist/min/dropzone.min.css" type="text/css"/>
    <link href="https://cdn.jsdelivr.net/npm/dv-holdon@0.0.1/src/download/HoldOn.min.css" rel="stylesheet"/>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/gh/gauravnewton/js-libs/notify/css/notify.css" />
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/jquery-confirm/3.3.4/jquery-confirm.min.css"/>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/gh/gauravnewton/json-checkable-tree/jquery.jsontree.css"/>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css" />
</head>
<body>
<h2 class="text-center">Kubernetes Profile Management System</h2>
<hr/>
<div class="container-fluid">
    <div class="row">
        <div class="col-sm-12">
            <button type="button" class="btn btn-info float-right" data-toggle="modal" data-target="#sampleEksConfigYamlModel">
              Sample eks config yaml file
            </button>
            <button type="button" class="btn btn-success mr-1 float-right" data-toggle="modal" data-target="#uploadEksConfigYamlModal">
              Add new eks config to list
            </button>
            <a type="button" class="btn btn-warning float-right mr-1" target="_blank" href="http://localhost:8080/swagger-ui/index.html">
              API documentation
            </a>
        </div>
    </div>
    <hr/>
    <div class="row">
        <div class="col-sm-12" style="overflow: auto; white-space: nowrap;">
            <table class="table table-striped table-hover table-condensed table-loader" id="eksConfigTable" >
                <thead>
                    <tr>
                        <td>Id</td>
                        <td>Profile Name</td>
                        <td>Action</td>
                    </tr>
                </thead>
            </table>
        </div>
    </div>
</div>



<!-- upload eksConfig yaml modal -->
<div class="modal fade" id="sampleEksConfigYamlModel" tabindex="-1" role="dialog" aria-labelledby="sampleEksConfigYamlModelTitle" aria-hidden="true">
  <div class="modal-dialog modal-dialog-centered modal-xl " role="document">
    <div class="modal-content">
      <div class="modal-header">
        <h5 class="modal-title" id="sampleEksConfigYamlModelLongTitle">Sample eks config yaml file.</h5>
        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
          <span aria-hidden="true">&times;</span>
        </button>
      </div>
      <div class="modal-body">
          <div class="container">
              <div class="row">
                  <div class="col-sm-12" id="parsedYamlDiv">
                    <pre>
                        apiVersion: v1
                        clusters:
                        - cluster:
                            certificate-authority-data: VVSG5kbktsamRVCkZ6ajl1SzdlNDlTamFINjFwYnJPUUcyUGk4MU92UTFvPQ0V3L1gxY2g4NGU4a0RPcUpFbHN4TCs2ZHZIdW1weEp6SEI3VlBMR0IKYXNwZDJPd0lQWjdzVnNtVW9PUitTS096Y3UxbEZEM2hKbkNHVVY3VnFQeWxsUFJTK1BLcFNnSDdDNE42UENHUQpxRE1GV2Y0V2F0REtML09ZdExwZWNQa2pUWTZhb0EzR1dKRnZGaDJ0VzlSVHBGSWNEMDRmWERLM3RKdkViOURSeGlJQVdiR3lYYb3BmCmdCaFNsZDhUWUdWaVFLMGYwWU1jS0dhd2VpQlhxYit5Wlh3RGJRTjQ5a2dnOUFBUlkwRzhCWjg5OStuakRzQm0KSUZiL241WXYvWSs1Ci0tLS0tRU5EIENFUlRJRklDQVRFLS0tLS0KLS0tLS1CRUdJTiBDRVJUSUZJQ0FURS0tLS0tCk1JSURCVENDQWUyZ0F3SUJBZ0lJQWJLQ3pwY3NWTkF3Rk84ejdtdFcxRUEKWUVFK3NrbGV5Qm5TclBsVWVlNDJIMSszTHBIa2oyMUxuVE5uem5zSXY4R0RqOWFQOU5hODhXRnd2bWlNZGtJRgp2Wi9XMDUvVjBkMU1iWkNaS1hhMGtEM2ZTRTJoQWdNQkFBR2pXVEJYTUE0R0ExVWREd0VCL3dRRUF3SUNwREFQCkJnTlZIUk1CQWY4RUJUQURBUUgvTUIwR0ExVWREZ1FXQkJUamtuSEIyV1Z4SWJ2WWJLNkdEbnkzemhCL0h6QVYKQmdOVkhSRUVEakFNZ2dwcmRXSmxjbTVsZEdWek1BMEdDU3FHU0liM0RRRUJDd1VBQTRJQkFRQ2NnVVJka3pPOApxTDhLbSt4QVVqNnBiSitvbnlPVnplNVBBMlNSQ3FTVGxJUzlJc0dWZENoOGNBY3NuMk1TZ1V3VllMMHNXQjBDCkI3eTk4L3FoZWdGcWFzFFZSktvWklodmNOQVFFTEJRQXdGVEVUTUJFR0ExVUUKQXhNS2EzVmlaWEp1WlhSbGN6QWVGdzB5TkRFd01qRXhNREkzTlRkYUZ3MHpOREV3TVRreE1ETXlOVGRhTUJVeApFekFSQmdOVkJBTVRDbXQxWW1WeWJtVjBaWE13Z2dFaU1BMEdDU3FHU0liM0RRRUJBUVVBQTRJQkR3QXdnZ0VLCkFvSUJBUUMxb29BWkZDSUVXT2d4WGMrTS9zZ3FDTXRmSldBRTJQdkJjbHNkR3FDL2paazhlRlRSckppTXFZLysKUzl0K0labnZYeDAxTjNVVXViMWxYWjhUN1VWMEprTzBoVXlKcWEybGQ4Z1g5VzVhd1B2UVRFeUsxdTV4TFJOcQo2UFFqWVlhNTBpWmNaTGR4dHBQb2ZvYUFMSFVzdzNaRXNyU1Z6VzN2V3ZMK0R6cWRkaHh3Q1jJzUVFRMm1jSOXV1bmYxK0J
                            server: https://8FDE7667FB3DA3D76DA58D4B651EF537.gr2.eu-west-3.eks.amazonaws.com
                          name: arn:aws:eks:eu-west-3:335425316266:cluster/abcd-eks-cluster
                        contexts:
                        - context:
                            cluster: arn:aws:eks:eu-west-3:335425316266:cluster/abcd-eks-cluster
                            user: arn:aws:eks:eu-west-3:335425316266:cluster/abcd-eks-cluster
                          name: arn:aws:eks:eu-west-3:335425316266:cluster/abcd-eks-cluster
                        current-context: arn:aws:eks:eu-west-3:335425316266:cluster/abcd-eks-cluster
                        kind: Config
                        preferences: {}
                        users:
                        - name: arn:aws:eks:eu-west-3:335425316266:cluster/abcd-eks-cluster
                          user:
                            exec:
                              apiVersion: client.authentication.k8s.io/v1beta1
                              args:
                              - --region
                              - eu-west-3
                              - eks
                              - get-token
                              - --cluster-name
                              - abcd-eks-cluster
                              - --output
                              - json
                              command: aws
                    <pre>
                  </div>
              </div>
          </div>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
      </div>
    </div>
  </div>
</div>

<!-- upload eksConfig yaml modal -->
<div class="modal fade" id="uploadEksConfigYamlModal" tabindex="-1" role="dialog" aria-labelledby="uploadEksConfigYamlModalCenterTitle" aria-hidden="true">
  <div class="modal-dialog modal-dialog-centered modal-xl " role="document">
    <div class="modal-content">
      <div class="modal-header">
        <h5 class="modal-title" id="uploadEksConfigYamlModalLongTitle">Upload a new eks config yaml file.</h5>
        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
          <span aria-hidden="true">&times;</span>
        </button>
      </div>
      <div class="modal-body">
        <form action="/file-upload" class="dropzone" id="eksConfigYamlForm"></form>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
        <button type="button" class="btn btn-primary" id="uploadBtn">Upload</button>
      </div>
    </div>
  </div>
</div>


<!-- parsed eksConfig yaml modal -->
<div class="modal fade" id="parsedEksConfigYamlModal" tabindex="-1" role="dialog" aria-labelledby="parsedEksConfigYamlModalCenterTitle" aria-hidden="true">
  <div class="modal-dialog modal-dialog-centered modal-xl modal-dialog-scrollable" role="document">
    <div class="modal-content" style="min-height:260px">
      <div class="modal-header">
        <h5 class="modal-title" id="parsedEksConfigYamlModalLongTitle">Choose properties to store under profile</h5>
        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
          <span aria-hidden="true">&times;</span>
        </button>
      </div>
      <div class="modal-body">
        <div class="container">
            <div class="row">
                <div class="col-sm-9">

                </div>
                <div class="col-sm-3">
                    <input type="text" class="form-control" placeholder="profile name" name="profileName" id="profileName"/>
                </div>
                <div class="col-sm-6">
                    <strong>Parsed Yaml Json</strong>
                    <ul id="parsedYamlJSON" class="border border-dark rounded h-100 d-inline-block w-100">

                    </ul>
                </div>

                <div class="col-sm-6">
                    <strong>Selected properties</strong>
                    <pre id="selectedYamlJSON" class="border border-dark rounded h-100 d-inline-block w-100">

                    </pre>
                </div>
            </div>
        </div>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
        <button type="button" class="btn btn-primary" id="addToProfile">Add to profile</button>
      </div>
    </div>
  </div>
</div>

<!-- parsed eksConfig yaml modal -->
<div class="modal fade" id="metaDataModal" tabindex="-1" role="dialog" aria-labelledby="metaDataModalCenterTitle" aria-hidden="true">
  <div class="modal-dialog modal-dialog-centered modal-xl modal-dialog-scrollable" role="document">
    <div class="modal-content" style="min-height:260px">
      <div class="modal-header">
        <h5 class="modal-title" id="metaDataModalLongTitle">All MetaData attached to Eks Config profile <span id="eksConfigId"></span></h5>
        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
          <span aria-hidden="true">&times;</span>
        </button>
      </div>
      <div class="modal-body">
        <div class="container">
            <div class="row">
                <div class="col-sm-12" style="overflow: auto; white-space: nowrap;">
                    <table class="table table-striped table-hover table-condensed table-loader" id="metaDataTable" >
                        <thead>
                            <tr>
                                <td>Id</td>
                                <td>Metadata Name</td>
                                <td>Metadata Value</td>
                                <td>Action</td>
                            </tr>
                        </thead>
                    </table>
                </div>
            </div>
        </div>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
      </div>
    </div>
  </div>
</div>

<script src="https://cdn.jsdelivr.net/gh/gauravnewton/js-libs/jquery/jquery.min.js"></script>
<script src="https://cdn.jsdelivr.net/gh/gauravnewton/js-libs/bootstrap/js/bootstrap.min.js"></script>
<script src="https://cdn.jsdelivr.net/gh/gauravnewton/js-libs/dataTable/js/dataTable.js"></script>
<script src="https://cdn.jsdelivr.net/gh/gauravnewton/js-libs/dataTable/js/dataTable.bootstrap.js"></script>
<script src="https://cdn.jsdelivr.net/gh/gauravnewton/js-libs/dataTable/js/dataTable.responsive.js"></script>
<script src="https://cdn.jsdelivr.net/gh/gauravnewton/js-libs/dataTable/js/responsive.bootstrap.min.js"></script>
<script src="https://unpkg.com/dropzone@5/dist/min/dropzone.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/dv-holdon@0.0.1/src/download/HoldOn.min.js"></script>
<script src="https://cdn.jsdelivr.net/gh/gauravnewton/js-libs/notify/js/notify.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery-confirm/3.3.4/jquery-confirm.min.js"></script>
<script src="https://cdn.jsdelivr.net/gh/gauravnewton/json-checkable-tree/jquery.jsontree.js"></script>

<script>
    var eksConfigTable;
    var metaDataTable
    $('.tree-plus').addClass('modal-dialog-scrollable');

    var renderSelectedYaml = function(json) {
        $('#selectedYamlJSON').html(JSON.stringify(json, null, 2));
    }

    var loadAllMetaDataForEksConfigId = function(id) {
        openLoader('Loading all meta data for Eks Config id '+id+'...');
        $.ajax({
            url: '/eks/getAllMetaDataForEksConfigId/'+id,
            type: 'GET',
            crossDomain: true,
            dataType: 'json',
            contentType: 'application/json',
            success: function(response) {
                if(response && response.status == "OK"){
                    jNotify.success('Success', response.message, {
                        delay: 2500,
                        fadeDelay: 1000,
                        closeButton: false,
                        titleBold: true,
                        offset: 40
                    });
                    closeLoader();
                    renderMetaDataDatTable(response.data);
                } else {
                    closeLoader();
                    jNotify.error('Error', response.message, {
                        delay: 2500,
                        fadeDelay: 1000,
                        closeButton: false,
                        titleBold: true,
                        offset: 40
                    });
                }
            },
            error: function(data, textStatus, errorMessage) {
                closeLoader();
                jNotify.error('Error', errorMessage, {
                    delay: 2500,
                    fadeDelay: 1000,
                    closeButton: false,
                    titleBold: true,
                    offset: 40
                });
            }
        });
    }

    var renderMetaDataDatTable = function(data) {
        if (metaDataTable) {
            metaDataTable.fnClearTable();
            metaDataTable.fnDestroy();
        }
        metaDataTable = $("#metaDataTable").dataTable({
            "aaData" : data,
            "autoWidth": false,
            "scrollX": true,
            "paging": true,
            "lengthChange": true,
            "searching": true,
            "ordering": true,
            "info": true,
            "oLanguage" : {
                "sEmptyTable" : "No Eks Meta data Config available",
            },
            "sortable": true,
            "aoColumns" : [
                {
                    mData : 'id',
                    sClass: 'text-center vertically-align'
                },
                {
                    mData : null,
                    sClass: 'text-center vertically-align'
                },
                {
                  mData : null,
                  sClass: 'text-center vertically-align'
                },
                {
                  mData : null,
                  sClass: 'text-center vertically-align'
                }
            ],
            "aoColumnDefs" : [
               {
                   "aTargets" : [ 1 ],
                   "mRender" : function ( data, type, bomModel ) {
                            var html = `<input type="text" name="metaDataKeyEditText-`+bomModel.id+`" id= "metaDataKeyEditText-`+bomModel.id+`" class="form-control d-none" />
                                    <span id="metaDataKeyText-`+bomModel.id+`" >`+bomModel.key+`</span>`;
                            return html;
                    }
               },
               {
                  "aTargets" : [ 2 ],
                  "mRender" : function ( data, type, bomModel ) {
                           var html = `<input type="text" name="metaDataValueEditText-`+bomModel.id+`" id= "metaDataValueEditText-`+bomModel.id+`" class="form-control d-none" />
                                   <span id="metaDataValueText-`+bomModel.id+`" >`+bomModel.value+`</span>`;
                           return html;
                   }
              },
              {
                "aTargets" : [ 3 ],
                "mRender" : function ( data, type, bomModel ) {
                        var html = `<div>
                                <a class="btn btn-primary" title="Edit this Metadata" id="editMetaDataActionIcon-`+bomModel.id+`" onClick="fireMetaDataEditAction('`+bomModel.id+`')" ><i class="fa fa-edit actionEvent"></i></a>
                                <a class="btn btn-success d-none" title="Save this Metadata" id="saveMetaDataActionIcon-`+bomModel.id+`" onClick="fireMetaDataSaveAction('`+bomModel.id+`')"><i class="fa fa-save"></i></a>
                                <a class="btn btn-danger" title="Delete this Metadata"id="deleteMetadataActionIcon-`+bomModel.id+`" onClick="fireMetadataDeleteAction('`+bomModel.id+`')"><i class="fa fa-trash actionEvent"></i></a>
                            </div>`;
                        return html;
                }
              }
            ]
        });
    }

    var patchMeta = function(id, key, value) {
        openLoader('Patching Selected Meta data...');
        $.ajax({
            url: '/eks/updateMetaData/'+id+'?key='+key+"&value="+value,
            type: 'PATCH',
            crossDomain: true,
            dataType: 'json',
            contentType: 'application/json',
            success: function(response) {
                if(response && response.status == "OK"){
                    jNotify.success('Success', response.message, {
                        delay: 2500,
                        fadeDelay: 1000,
                        closeButton: false,
                        titleBold: true,
                        offset: 40
                    });
                    closeLoader();
                    loadAllMetaDataForEksConfigId($('#eksConfigId').html());
                } else {
                    closeLoader();
                    jNotify.error('Error', response.message, {
                        delay: 2500,
                        fadeDelay: 1000,
                        closeButton: false,
                        titleBold: true,
                        offset: 40
                    });
                }
            },
            error: function(data, textStatus, errorMessage) {
                closeLoader();
                jNotify.error('Error', errorMessage, {
                    delay: 2500,
                    fadeDelay: 1000,
                    closeButton: false,
                    titleBold: true,
                    offset: 40
                });
            }
        });
    }

    var patchEksConfig = function(id, userName) {
        openLoader('Patching Selected Eks Config...');
        $.ajax({
            url: '/eks/updateEksConfig/'+id+'?userName='+userName,
            type: 'PATCH',
            crossDomain: true,
            dataType: 'json',
            contentType: 'application/json',
            success: function(response) {
                if(response && response.status == "OK"){
                    jNotify.success('Success', response.message, {
                        delay: 2500,
                        fadeDelay: 1000,
                        closeButton: false,
                        titleBold: true,
                        offset: 40
                    });
                    closeLoader();
                    loadAllEksConfigProfiles();
                } else {
                    closeLoader();
                    jNotify.error('Error', response.message, {
                        delay: 2500,
                        fadeDelay: 1000,
                        closeButton: false,
                        titleBold: true,
                        offset: 40
                    });
                }
            },
            error: function(data, textStatus, errorMessage) {
                closeLoader();
                jNotify.error('Error', errorMessage, {
                    delay: 2500,
                    fadeDelay: 1000,
                    closeButton: false,
                    titleBold: true,
                    offset: 40
                });
            }
        });
    }

    var fireMetadataDeleteAction = function(id) {
        $.confirm({
            title: 'Alert !',
            content: 'Are you sure to delete this Metadata ?',
            theme: 'black',
            useBootstrap: false,
            boxWidth: '500px',
            buttons: {
                confirm: function() {
                    openLoader('Deleting Metadata... ');
                    deleteMetaData(id);
                },
                cancel: function() {
                    jNotify.info('Info', 'User cancelled upload!', {
                        delay: 2500,
                        fadeDelay: 1000,
                        closeButton: false,
                        titleBold: true,
                        offset: 40
                    });
                }
            }
        });
    }

    var deleteMetaData = function(id) {
        openLoader('Deleting Selected Meta data...');
        $.ajax({
            url: '/eks/deleteMetaData/'+id,
            type: 'DELETE',
            crossDomain: true,
            dataType: 'json',
            contentType: 'application/json',
            success: function(response) {
                if(response && response.status == "OK"){
                    jNotify.success('Success', response.message, {
                        delay: 2500,
                        fadeDelay: 1000,
                        closeButton: false,
                        titleBold: true,
                        offset: 40
                    });
                    closeLoader();
                    loadAllMetaDataForEksConfigId($('#eksConfigId').html());
                } else {
                    closeLoader();
                    jNotify.error('Error', response.message, {
                        delay: 2500,
                        fadeDelay: 1000,
                        closeButton: false,
                        titleBold: true,
                        offset: 40
                    });
                }
            },
            error: function(data, textStatus, errorMessage) {
                closeLoader();
                jNotify.error('Error', errorMessage, {
                    delay: 2500,
                    fadeDelay: 1000,
                    closeButton: false,
                    titleBold: true,
                    offset: 40
                });
            }
        });
    }

    var deleteEksConfig = function(id) {
        openLoader('Deleting Selected Eks Config...');
        $.ajax({
            url: '/eks/deleteEksConfig/'+id,
            type: 'DELETE',
            crossDomain: true,
            dataType: 'json',
            contentType: 'application/json',
            success: function(response) {
                if(response && response.status == "OK"){
                    jNotify.success('Success', response.message, {
                        delay: 2500,
                        fadeDelay: 1000,
                        closeButton: false,
                        titleBold: true,
                        offset: 40
                    });
                    closeLoader();
                    loadAllEksConfigProfiles();
                } else {
                    closeLoader();
                    jNotify.error('Error', response.message, {
                        delay: 2500,
                        fadeDelay: 1000,
                        closeButton: false,
                        titleBold: true,
                        offset: 40
                    });
                }
            },
            error: function(data, textStatus, errorMessage) {
                closeLoader();
                jNotify.error('Error', errorMessage, {
                    delay: 2500,
                    fadeDelay: 1000,
                    closeButton: false,
                    titleBold: true,
                    offset: 40
                });
            }
        });
    }

    var fireMetaDataEditAction = function(id) {
        $('#metaDataKeyText-'+id).toggleClass('d-none');
        $('#metaDataValueText-'+id).toggleClass('d-none');
        $('#metaDataKeyEditText-'+id).toggleClass('d-none');
        $('#metaDataValueEditText-'+id).toggleClass('d-none');
        $('#saveMetaDataActionIcon-'+id).toggleClass('d-none');
        $('#editMetaDataActionIcon-'+id).toggleClass('d-none');
        $('#metaDataKeyEditText-'+id).val($('#metaDataKeyText-'+id).html());
        $('#metaDataValueEditText-'+id).val($('#metaDataValueText-'+id).html());
    }

    var fireMetaDataSaveAction = function(id) {
        $('#metaDataKeyText-'+id).toggleClass('d-none');
        $('#metaDataValueText-'+id).toggleClass('d-none');
        $('#metaDataKeyEditText-'+id).toggleClass('d-none');
        $('#metaDataValueEditText-'+id).toggleClass('d-none');
        $('#saveMetaDataActionIcon-'+id).toggleClass('d-none');
        $('#editMetaDataActionIcon-'+id).toggleClass('d-none');
        patchMeta(id, $('#metaDataKeyEditText-'+id).val(), $('#metaDataValueEditText-'+id).val());
    }

    var fireEksConfigSaveAction = function(id) {
        var userName = $('#userNameEditText-'+id).val();
        $('#userNameText-'+id).toggleClass('d-none');
        $('#userNameEditText-'+id).toggleClass('d-none');
        $('#saveEksConfigActionIcon-'+id).toggleClass('d-none');
        $('#editEksConfigActionIcon-'+id).toggleClass('d-none');
        patchEksConfig(id, userName);
    }

    var fireMetaDataViewAction = function(id) {
        $('#metaDataModal').modal('show');
        $('#eksConfigId').html(id);
        loadAllMetaDataForEksConfigId(id);
    }

    var fireEksConfigDeleteAction = function(id) {
        $.confirm({
            title: 'Alert !',
            content: 'Are you sure to delete this Eks config ?',
            theme: 'black',
            useBootstrap: false,
            boxWidth: '500px',
            buttons: {
                confirm: function() {
                    openLoader('Deleting Eks Config... ');
                    deleteEksConfig(id);
                },
                cancel: function() {
                    jNotify.info('Info', 'User cancelled upload!', {
                        delay: 2500,
                        fadeDelay: 1000,
                        closeButton: false,
                        titleBold: true,
                        offset: 40
                    });
                }
            }
        });
    }

    var fireEksConfigEditAction = function(id){
    	$('#userNameText-'+id).toggleClass('d-none');
    	$('#userNameEditText-'+id).toggleClass('d-none');
    	$('#saveEksConfigActionIcon-'+id).toggleClass('d-none');
    	$('#editEksConfigActionIcon-'+id).toggleClass('d-none');
        $('#userNameEditText-'+id).val($('#userNameText-'+id).html());
    }

    var renderEksConfigDatTable = function(data) {
        if (eksConfigTable) {
            eksConfigTable.fnClearTable();
            eksConfigTable.fnDestroy();
        }
        eksConfigTable = $("#eksConfigTable").dataTable({
            "aaData" : data,
            "autoWidth": false,
            "scrollX": true,
            "paging": true,
            "lengthChange": true,
            "searching": true,
            "ordering": true,
            "info": true,
            "oLanguage" : {
                "sEmptyTable" : "No Eks Config available"
            },
            "sortable": true,
            "aoColumns" : [
                {
                    mData : 'id',
                    sClass: 'text-center vertically-align'
                },
                {
                    mData : null,
                    sClass: 'text-center vertically-align'
                },
                {
                  mData : null,
                  sClass: 'text-center vertically-align'

                }
            ],
            "aoColumnDefs" : [
               {
                   "aTargets" : [ 1 ],
                   "mRender" : function ( data, type, bomModel ) {
                            var html = `<input type="text" name="userNameEditText-`+bomModel.id+`" id= "userNameEditText-`+bomModel.id+`" class="form-control d-none" />
                                    <span id="userNameText-`+bomModel.id+`" >`+bomModel.userName+`</span>`;
                            return html;
                    }
               },
              {
                "aTargets" : [ 2 ],
                "mRender" : function ( data, type, bomModel ) {
                        var html = `<div>
                                <a class="btn btn-info" title="View attached metadata" id="viewMetaDataActionIcon-`+bomModel.id+`" onClick="fireMetaDataViewAction('`+bomModel.id+`')" ><i class="fa fa-eye actionEvent"></i></a>
                                <a class="btn btn-primary" title="Edit this EksConfig" id="editEksConfigActionIcon-`+bomModel.id+`" onClick="fireEksConfigEditAction('`+bomModel.id+`')" ><i class="fa fa-edit actionEvent"></i></a>
                                <a class="btn btn-success d-none" title="Save this EksConfig" id="saveEksConfigActionIcon-`+bomModel.id+`" onClick="fireEksConfigSaveAction('`+bomModel.id+`')"><i class="fa fa-save"></i></a>
                                <a class="btn btn-danger" title="Delete this template"id="deleteEksConfigActionIcon-`+bomModel.id+`" onClick="fireEksConfigDeleteAction('`+bomModel.id+`')"><i class="fa fa-trash actionEvent"></i></a>
                            </div>`;
                        return html;
                }
              }
            ]
        });
    }

    var loadAllEksConfigProfiles = function() {
        openLoader('Loading all Eks Configs...');
        $.ajax({
            url: '/eks/getAllEksConfigProfile',
            type: 'GET',
            crossDomain: true,
            dataType: 'json',
            contentType: 'application/json',
            success: function(response) {
                if(response && response.status == "OK"){
                    jNotify.success('Success', response.message, {
                        delay: 2500,
                        fadeDelay: 1000,
                        closeButton: false,
                        titleBold: true,
                        offset: 40
                    });
                    closeLoader();
                    renderEksConfigDatTable(response.data);
                } else {
                    closeLoader();
                    jNotify.error('Error', response.message, {
                        delay: 2500,
                        fadeDelay: 1000,
                        closeButton: false,
                        titleBold: true,
                        offset: 40
                    });
                }
            },
            error: function(data, textStatus, errorMessage) {
                closeLoader();
                jNotify.error('Error', response.message, {
                    delay: 2500,
                    fadeDelay: 1000,
                    closeButton: false,
                    titleBold: true,
                    offset: 40
                });
            }
        });
    }

    var addEksConfigToProfile = function() {
        var metaDataList = [];
        var selectedItems = $("#parsedYamlJSON").jsontree("getSelectedItems");
        $(selectedItems).each(function(key, value){
            var metaData = {
                'key' : value.key,
                'value' : value.value
            };
            metaDataList.push(metaData);
        });


        var eksPayload = {
            'metaData' : metaDataList,
            'userName' : $('#profileName').val()
        }

        openLoader('Adding eks config to profile...');
        $.ajax({
            url: '/eks/addEksConfigToProfile',
            type: 'POST',
            crossDomain: true,
            dataType: 'json',
            data: JSON.stringify(eksPayload),
            contentType: 'application/json',
            success: function(response) {
                if (response && response.status == "CREATED"){
                    jNotify.success('Success', response.message, {
                        delay: 2500,
                        fadeDelay: 1000,
                        closeButton: false,
                        titleBold: true,
                        offset: 40
                    });
                    $('#parsedEksConfigYamlModal').modal('hide');
                    closeLoader();
                    loadAllEksConfigProfiles();
                } else{
                    closeLoader();
                    jNotify.error('Error', response.message, {
                        delay: 2500,
                        fadeDelay: 1000,
                        closeButton: false,
                        titleBold: true,
                        offset: 40
                    });
                }
            },
            error: function(data, textStatus, errorMessage) {
                closeLoader();
                jNotify.error('Error', response.message, {
                    delay: 2500,
                    fadeDelay: 1000,
                    closeButton: false,
                    titleBold: true,
                    offset: 40
                });
            }
        });
    }

    $('#addToProfile').on('click', function() {
        if ($('#profileName').val() == '') {
            jNotify.error('Error', 'Please add a profile name first', {
                delay: 2500,
                fadeDelay: 1000,
                closeButton: false,
                titleBold: true,
                offset: 40
            });
            return;
        }
        addEksConfigToProfile();
    });

    $("#parsedYamlJSON").on("unselected.item.jsontree", function(ev, data) {
      var result = {
          "event" : "unselected",
          "itemInfo": data.data
      };
      var selectedItems = $("#parsedYamlJSON").jsontree("getSelectedItems");
      renderSelectedYaml(selectedItems);

    }).on("selected.item.jsontree", function(ev, data) {
      var result = {
          "event" : "selected",
          "itemInfo": data.data
      };
      var selectedItems = $("#parsedYamlJSON").jsontree("getSelectedItems");
      renderSelectedYaml(selectedItems);
    });

    var renderParsedYaml = function(json) {
        $("#parsedYamlJSON").jsontree({json});
    }

    var openLoader = function(message) {
        var options = {
             theme: "sk-rect",
             message: message,
             backgroundColor: "#000",
             textColor: "white"
        };
        HoldOn.open(options);
    }

    var closeLoader = function() {
        HoldOn.close();
    }

    $("#parsedEksConfigYamlModal").on("hidden.bs.modal", function () {
        $('#selectedYamlJSON').html('');
    });

    var fileAcceptedByDropZone = false;
    Dropzone.autoDiscover = false;
    $(function() {
        myDropzone = new Dropzone('#eksConfigYamlForm', {
            addRemoveLinks: true,
            autoProcessQueue: false,
            uploadMultiple: false,
            parallelUploads: 100,
            maxFiles: 1,
            paramName: 'file',
            clickable: true,
            acceptedFiles: 'application/x-yaml',
            url: '/eks/uploadEksConfig',
            dictDefaultMessage: 'Drag/click here to upload els config yaml',
            init: function() {
                var myDropzone = this;
                $('#uploadBtn').click(function(e) {
                    e.preventDefault();
                    if (true) {
                        if (myDropzone.files.length == 0) {
                            jNotify.error('Error', 'Choose a file first', {
                                delay: 2500,
                                fadeDelay: 1000,
                                closeButton: false,
                                titleBold: true,
                                offset: 40
                            });
                            return;
                        }
                        $.confirm({
                            title: 'Alert !',
                            content: 'Are you sure to upload this file to process ?',
                            theme: 'black',
                            useBootstrap: false,
                            boxWidth: '500px',
                            buttons: {
                                confirm: function() {
                                    openLoader('Processing file... ');
                                    myDropzone.processQueue();
                                },
                                cancel: function() {
                                    jNotify.info('Info', 'User cancelled upload!', {
                                        delay: 2500,
                                        fadeDelay: 1000,
                                        closeButton: false,
                                        titleBold: true,
                                        offset: 40
                                    });
                                }
                            }
                        });
                    }
                    return false;
                });
                this.on('thumbnail', function(file) {
                    /* if ( file.width != 720 || file.height != 480 ) {
                        file.rejectDimensions();
                        fileAcceptedByDropZone = false;
                    }
                    else {
                        file.acceptDimensions();
                        fileAcceptedByDropZone = true;
                    } */
                    file.acceptDimensions();
                    fileAcceptedByDropZone = true;
                });
                this.on('sending', function(file, xhr, formData) {
                    // Append all form inputs to the formData Dropzone will POST
                    /* var data = $('#blog_post').serializeArray();
                    $.each(data, function(key, el) {
                        formData.append(el.name, el.value);
                    });
                    formData.append('content', CKEDITOR.instances['artical'].getData());
                    formData.append('uploadedBy', '<?php echo  $this->session->userdata('username'); ?>'); */
                    console.log(formData);
                });
            },
            accept: function(file, done) {
                done();
            },
            success: function(file, response) {
                $('#uploadEksConfigYamlModal').modal('hide');
                if(response && response.status == "OK"){
                    jNotify.success('Success', response.message, {
                        delay: 2500,
                        fadeDelay: 1000,
                        closeButton: false,
                        titleBold: true,
                        offset: 40
                    });
                } else {
                    jNotify.error('Error', response.message, {
                        delay: 2500,
                        fadeDelay: 1000,
                        closeButton: false,
                        titleBold: true,
                        offset: 40
                    });
                }
                closeLoader();
                this.removeAllFiles(true);
                renderParsedYaml(response.data);
                $('#parsedEksConfigYamlModal').modal('show');
            },
            complete: function(file) {
                this.removeAllFiles(true);
                closeLoader();
            },
            error: function(file, response) {
                if ($.type(response) === "string")
                    var message = response; //dropzone sends it's own error messages in string
                else
                    var message = response.message;
                file.previewElement.classList.add("dz-error");
                _ref = file.previewElement.querySelectorAll("[data-dz-errormessage]");
                _results = [];
                for (_i = 0, _len = _ref.length; _i < _len; _i++) {
                    node = _ref[_i];
                    _results.push(node.textContent = message);
                }
                $('#uploadEksConfigYamlModal').modal('hide');
                jNotify.error('Error', response.message, {
                    delay: 2500,
                    fadeDelay: 1000,
                    closeButton: false,
                    titleBold: true,
                    offset: 40
                });
                this.removeAllFiles(true);
                return _results;
            },
            successmultiple: function(file, response) {
                this.removeAllFiles(true);
                console.log(file, response);
            },
            completemultiple: function(file, response) {
                this.removeAllFiles(true);
                console.log(file, response, "completemultiple");
            },
            reset: function() {
                console.log("resetFiles");
                this.removeAllFiles(true);
            }
        });

        loadAllEksConfigProfiles();
    })

</script>
</body>
</html>