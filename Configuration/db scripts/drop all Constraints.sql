
declare @str varchar(max)
declare cur cursor for

SELECT 'ALTER TABLE ' + '[' + s.[NAME] + '].[' + t.name + '] DROP CONSTRAINT ['+ c.name + ']'
FROM sys.objects c, sys.objects t, sys.schemas s
WHERE c.type IN ('C', 'F', 'PK', 'UQ', 'D')
 AND c.parent_object_id=t.object_id and t.type='U' AND t.SCHEMA_ID = s.schema_id
ORDER BY c.type

open cur
FETCH NEXT FROM cur INTO @str
WHILE (@@fetch_status = 0) BEGIN
 PRINT @str
 EXEC (@str)
 FETCH NEXT FROM cur INTO @str
END

close cur
deallocate cur