
require 'time'

import 'org.apache.hadoop.hbase.client.ConnectionFactory'
import 'org.apache.hadoop.hbase.client.Connection'
import 'org.apache.hadoop.hbase.client.Table'
import 'org.apache.hadoop.hbase.TableName'
import 'org.apache.hadoop.hbase.client.Put'
import 'javax.xml.stream.XMLStreamConstants'

def jbytes(*args)
  args.map { |arg| arg.to_s.to_java_bytes }
end

factory = javax.xml.stream.XMLInputFactory.newInstance
reader = factory.createXMLStreamReader(java.lang.System.in)

document = nil
buffer = nil
count = 0

connection = ConnectionFactory.createConnection(@hbase.configuration);

table = connection.getBufferedMutator(TableName.valueOf("foods"));


while reader.has_next
  type = reader.next
  if type == XMLStreamConstants::START_ELEMENT
case reader.local_name
    when 'page' then document = {}
    when /Food_Code|Display_Name|Calories/ then buffer = []
    end

  elsif type == XMLStreamConstants::CHARACTERS

    buffer << reader.text unless buffer.nil?
                                                      

  elsif type == XMLStreamConstants::END_ELEMENT

    case reader.local_name
    when /Food_Code|Display_Name|Calories/
      document[reader.local_name] = buffer.join
      when 'Food_Display_Row'
      key = document['Food_Code'].to_java_bytes
      

      p = Put.new(key)
       p.addColumn(*jbytes("text", "", document['text']))
      p.addColumn(*jbytes("Food_Display_Row", "name", document['Display_Name']))
      p.addColumn(*jbytes("Food_Display_Row", "cal", document['Calories']))

      table.mutate(p)
      
      count += 1
      table.flush() if count % 10 == 0
      if count % 500 == 0
        puts "#{count} records inserted (#{document['Display_Name']})"
      end
    end
  end
end

table.flush()

      
      
      