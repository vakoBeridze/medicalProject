create procedure VarCharToNvarChar
as
begin
declare curChangeTypes cursor for select column_name,table_name,character_maximum_length from information_schema.columns where DATA_TYPE='varchar' and table_name in (select distinct table_Name from information_schema.columns where DATA_TYPE='varchar')
open curChangeTypes
declare @cn varchar(50),@tn varchar(50),@cml int
declare @str varchar(8000)
Fetch Next From curChangeTypes into @cn,@tn,@cml
while(@@fetch_status=0)
begin
if(@cml=-1)
set @str = 'alter table '+@tn+' alter  column '+@cn+' nvarchar(max)'
else
set @str = 'alter table '+@tn+' alter  column '+@cn+' nvarchar('+cast(@cml as varchar)+')'
exec(@str)
Fetch Next From curChangeTypes into @cn,@tn,@cml
end
close curChangeTypes
deallocate curChangeTypes
end