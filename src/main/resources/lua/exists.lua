--[[
判断任务对应的切片ID缓存是否存在，如果存在的话，将切片ID添加到缓存中。否则不进行任何操作。
--]]

local num = redis.call("EXISTS", KEYS[1])
if num == 1 then
    redis.call('RPUSH', KEYS[1], ARGV[1])
    return 1
end

return 0