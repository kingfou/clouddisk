getAllMessageByName
===
select
extractvalue(content, '/function/name') as name,
extractvalue(content,'/function/param/elem/attribute::name') as params,
extractvalue(content,'/function/param/elem/attribute::for') as paramsfor,
extractvalue(content,'/function/usage') as fusage,
extractvalue(content,'/function/relevant/func') as relevantfuncs,
extractvalue(content,'/function/example') as function from xmltable where 1=1
@if(!isEmpty(name)){
and extractvalue(content, '/function/name') like #'%'+name+'%'#
@}

getOneMessageByName
===
select
extractvalue(content, '/function/name') as name,
extractvalue(content,'/function/param/elem/attribute::name') as params,
extractvalue(content,'/function/param/elem/attribute::for') as paramsfor,
extractvalue(content,'/function/usage') as fusage,
extractvalue(content,'/function/relevant/func') as relevantfuncs,
extractvalue(content,'/function/example') as function from xmltable where 1=1
@if(!isEmpty(name)){
and extractvalue(content, '/function/name') = #name#
@}

getAllMessageByUsage
===
select
extractvalue(content, '/function/name') as name,
extractvalue(content,'/function/param/elem/attribute::name') as params,
extractvalue(content,'/function/param/elem/attribute::for') as paramsfor,
extractvalue(content,'/function/usage') as fusage,
extractvalue(content,'/function/relevant/func') as relevantfuncs,
extractvalue(content,'/function/example') as function from xmltable where 1=1
@if(!isEmpty(usage)){
and extractvalue(content, '/function/usage') like   # '%'+usage+'%'#
@}





getAllName
===
select * from xmltable;
